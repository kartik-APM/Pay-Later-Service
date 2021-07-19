package com.practice.pay.later.service.service;

import com.practice.pay.later.service.model.Repayment;

import java.util.ArrayList;
import java.util.List;

public interface RepaymentService {

    void addRepayment(Repayment repayment, Long userId, Long accountId);

    List<ArrayList> getAllRepayment(Long accountId);
}
