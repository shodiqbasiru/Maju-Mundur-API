package com.msfb.maju_mundur_application.service.impl;

import com.msfb.maju_mundur_application.dto.request.ProductRequest;
import com.msfb.maju_mundur_application.dto.request.UpdateProductRequest;
import com.msfb.maju_mundur_application.dto.response.ProductResponse;
import com.msfb.maju_mundur_application.entity.Merchant;
import com.msfb.maju_mundur_application.entity.Product;
import com.msfb.maju_mundur_application.repository.ProductRepository;
import com.msfb.maju_mundur_application.service.MerchantService;
import com.msfb.maju_mundur_application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MerchantService merchantService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Product createProduct(ProductRequest request) {
        Merchant merchant = merchantService.getMerchantById(request.getMerchantId());

        Product product = Product.builder()
                .productName(request.getProductName())
                .price(request.getPrice())
                .stock(request.getStock())
                .isDelete(false)
                .merchant(merchant)
                .build();
        return productRepository.saveAndFlush(product);
    }

    @Transactional(readOnly = true)
    @Override
    public Product getById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public ProductResponse getProductById(String id) {
        Product product = getById(id);
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .stock(product.getStock())
                .isDelete(product.getIsDelete())
                .merchantId(product.getMerchant().getId())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll().stream()
                .filter(product -> !product.getIsDelete())
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .isDelete(product.getIsDelete())
                        .merchantId(product.getMerchant().getId())
                        .build()).toList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProductResponse updateProduct(UpdateProductRequest request) {
        Product currentProduct = getById(request.getId());
        currentProduct.setProductName(request.getProductName());
        currentProduct.setStock(request.getStock());
        currentProduct.setPrice(request.getPrice());

        Product product = productRepository.saveAndFlush(currentProduct);
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .stock(product.getStock())
                .isDelete(product.getIsDelete())
                .merchantId(product.getMerchant().getId())
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteProduct(String id) {
        Product product = getById(id);
        product.setIsDelete(true);
    }
}
