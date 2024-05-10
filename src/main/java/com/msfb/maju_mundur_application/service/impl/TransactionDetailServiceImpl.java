package com.msfb.maju_mundur_application.service.impl;

import com.msfb.maju_mundur_application.entity.TransactionDetail;
import com.msfb.maju_mundur_application.repository.TransactionDetailRepository;
import com.msfb.maju_mundur_application.service.TransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionDetailServiceImpl implements TransactionDetailService {

    private final TransactionDetailRepository trxDetailRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<TransactionDetail> createBulk(List<TransactionDetail> trxDetails) {
        return trxDetailRepository.saveAllAndFlush(trxDetails);
    }
}
