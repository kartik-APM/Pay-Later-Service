package com.practice.pay.later.service.service;

import com.practice.pay.later.service.dto.AccountDTO;
import com.practice.pay.later.service.exception.ApiResponse;
import com.practice.pay.later.service.model.Account;


public interface AccountService {

    ApiResponse<String> addAccountDetails(AccountDTO accountDTO, Long userId);

    ApiResponse<AccountDTO> getAccountDetails(Long userId);
}
