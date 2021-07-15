package com.practice.pay.later.service.service;


import com.practice.pay.later.service.model.Account;
import com.practice.pay.later.service.model.User;
import com.practice.pay.later.service.repository.AccountRepository;
import com.practice.pay.later.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void addAccountDetails(Account account, Long userId) {
        Date date = new Date();
        User userFromDb = userRepository.findById(userId).get();
        account.setUser(userFromDb);
        account.setCreatedAt(date);
        account.setUpdatedAt(date);
        accountRepository.save(account);
    }

//    @Override
//    public List<Account> getAllAccounts() {
//        return accountRepository.findAll();
//    }
}
