package com.practice.pay.later.service.service;


import com.practice.pay.later.service.model.Account;
import com.practice.pay.later.service.model.User;
import com.practice.pay.later.service.repository.AccountRepository;
import com.practice.pay.later.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void addAccountDetails(Account account, Long userId) {

        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate= DateFor.format(date);

        User userFromDb = userRepository.findById(userId).get();

        account.setUser(userFromDb);
        account.setDateAccountCreated(stringDate);
        account.setDateAccountUpdated(stringDate);
        account.setAvailableCreditLimit(account.getAuthorisedCreditLimit());
        accountRepository.save(account);
        System.out.println(account);
        System.out.println("Account Details Saved");
    }

    @Override
    public Account getAccountDetails(Long userId) {
        User userFromDb = userRepository.findById(userId).get();
        Account account = userFromDb.getAccount();
        System.out.println("Account Returned.");
        return account;
    }


}
