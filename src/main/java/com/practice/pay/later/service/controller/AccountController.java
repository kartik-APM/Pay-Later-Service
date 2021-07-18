package com.practice.pay.later.service.controller;


import com.practice.pay.later.service.model.Account;
import com.practice.pay.later.service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("users/{id}/accounts")
    public void addAccountDetails(@RequestBody Account account,
                                     @PathVariable("id") Long userId){
        accountService.addAccountDetails(account, userId);
    }

    @GetMapping("users/{id}/accounts")
    public Account getAccountDetails(@PathVariable("id") Long userId){
        return accountService.getAccountDetails(userId);
    }

}
