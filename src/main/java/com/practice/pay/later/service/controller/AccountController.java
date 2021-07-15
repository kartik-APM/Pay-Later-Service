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

    @PostMapping("/accounts/{id}")
    public void addAccountDetails(@RequestBody Account account,
                                     @PathVariable("id") Long userId){
        accountService.addAccountDetails(account, userId);
    }

//    @GetMapping("/accounts")
//    public List<Account> getAllAccounts(){
//        return accountService.getAllAccounts();
//    }

}
