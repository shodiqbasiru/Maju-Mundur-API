package com.msfb.maju_mundur_application.service.impl;

import com.msfb.maju_mundur_application.dto.request.TransactionRequest;
import com.msfb.maju_mundur_application.dto.response.CustomerInfoResponse;
import com.msfb.maju_mundur_application.dto.response.TransactionDetailResponse;
import com.msfb.maju_mundur_application.dto.response.TransactionResponse;
import com.msfb.maju_mundur_application.dto.response.TransactionResultResponse;
import com.msfb.maju_mundur_application.entity.*;
import com.msfb.maju_mundur_application.repository.TransactionRepository;
import com.msfb.maju_mundur_application.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerService customerService;
    private final TransactionDetailService trxDetailService;
    private final ProductService productService;
    private final UserService userService;
    private final MerchantService merchantService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TransactionResultResponse createTransaction(TransactionRequest request) {
        Customer customer = customerService.getById(request.getCustomerId());
        Merchant merchant = merchantService.getMerchantById(request.getMerchantId());
        Transaction transaction = Transaction.builder()
                .date(new Date())
                .customer(customer)
                .merchant(merchant)
                .build();
        transactionRepository.saveAndFlush(transaction);

        int oldReward = customer.getReward() != null ? customer.getReward() : 0;
        AtomicInteger totalReward = new AtomicInteger();
        AtomicInteger totalTransaction = new AtomicInteger();

        List<TransactionDetail> trxDetail = request.getDetailRequests().stream()
                .map(detailRequest -> {
                    Product product = productService.getById(detailRequest.getProductId());
                    if (product.getStock() - detailRequest.getQty() < 0)
                        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "out of stock");

                    product.setStock(product.getStock() - detailRequest.getQty());

                    int totalPrice = product.getPrice() * detailRequest.getQty();
                    totalTransaction.addAndGet(totalPrice);

                    return TransactionDetail.builder()
                            .product(product)
                            .transaction(transaction)
                            .qty(detailRequest.getQty())
                            .price(product.getPrice())
                            .build();
                }).toList();

        int priceLimits = 500000;
        if (totalTransaction.get() > priceLimits) {
            int rewardB = 40;
            totalReward.set(oldReward + rewardB);
        } else {
            int rewardA = 20;
            totalReward.set(oldReward + rewardA);
        }
        customer.setReward(totalReward.get());

        trxDetailService.createBulk(trxDetail);
        transaction.setTrxDetails(trxDetail);

        List<TransactionDetailResponse> trxResponse = trxDetail.stream()
                .map(detail -> TransactionDetailResponse.builder()
                        .id(detail.getId())
                        .qty(detail.getQty())
                        .price(detail.getPrice())
                        .productId(detail.getProduct().getId())
                        .build()).toList();
        return TransactionResultResponse.builder()
                .id(transaction.getId())
                .trxDate(transaction.getDate())
                .customer(CustomerInfoResponse.builder()
                        .id(transaction.getCustomer().getId())
                        .reward(totalReward.get() - oldReward)
                        .point(totalReward.get())
                        .build())
                .transactionDetails(trxResponse)
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<TransactionResponse> getAllTransaction() {
        String accountId = userService.getByContext().getId();
        return transactionRepository.findAll().stream()
                .filter(transaction ->
                        transaction.getMerchant().getAccount().getId().equals(accountId) ||
                                transaction.getCustomer().getAccount().getId().equals(accountId)
                )
                .map(transaction -> TransactionResponse.builder()
                        .id(transaction.getId())
                        .trxDate(transaction.getDate())
                        .customerName(transaction.getCustomer().getCustomerName())
                        .transactionDetails(transaction.getTrxDetails().stream()
                                .map(trxDetail -> TransactionDetailResponse.builder()
                                        .id(trxDetail.getId())
                                        .productId(trxDetail.getProduct().getId())
                                        .qty(trxDetail.getQty())
                                        .price(trxDetail.getPrice())
                                        .build())
                                .toList())
                        .build())
                .toList();
    }
}