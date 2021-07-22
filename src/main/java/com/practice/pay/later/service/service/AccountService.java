package com.practice.pay.later.service.service;

import com.practice.pay.later.service.model.Account;


public interface AccountService {

    void addAccountDetails(Account account, Long userId);

    Account getAccountDetails(Long userId);
}
