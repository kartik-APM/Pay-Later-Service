package com.practice.pay.later.service.service;


import com.practice.pay.later.service.model.DebitTransaction;
import com.practice.pay.later.service.model.Refund;

public interface RefundService {
    public void addRefund(Refund refund,
                          Long userId,
                          Long accountId);
}
