package com.practice.pay.later.service.service;

import com.practice.pay.later.service.dto.RepaymentDTO;
import com.practice.pay.later.service.exception.ApiResponse;
import com.practice.pay.later.service.model.Repayment;

import java.util.ArrayList;
import java.util.List;

public interface RepaymentService {

    ApiResponse<String> processRepaymentTransaction(RepaymentDTO repaymentDTO, Long userId);

    List<ArrayList> getAllRepayment(Long accountId);
}
