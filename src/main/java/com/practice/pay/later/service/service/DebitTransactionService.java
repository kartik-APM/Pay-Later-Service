package com.practice.pay.later.service.service;


import com.practice.pay.later.service.dto.DebitTransactionDTO;
import com.practice.pay.later.service.exception.ApiResponse;

import java.util.List;

public interface DebitTransactionService {

    ApiResponse<String> processDebitTransaction(DebitTransactionDTO debitTransactionDTO, Long userId);

    ApiResponse<List<DebitTransactionDTO>> getAllDebitTransaction(Long accountId);

}
