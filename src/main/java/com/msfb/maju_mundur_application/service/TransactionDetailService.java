package com.msfb.maju_mundur_application.service;

import com.msfb.maju_mundur_application.entity.TransactionDetail;

import java.util.List;

public interface TransactionDetailService {
    List<TransactionDetail> createBulk(List<TransactionDetail> trxDetails);
}
