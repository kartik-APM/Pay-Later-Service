package com.practice.pay.later.service.controller;


import ch.qos.logback.classic.Logger;
import com.practice.pay.later.service.converter.AccountConverter;
import com.practice.pay.later.service.dto.AccountDTO;
import com.practice.pay.later.service.exception.APIException;
import com.practice.pay.later.service.model.Account;
import com.practice.pay.later.service.model.User;
import com.practice.pay.later.service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired private AccountService accountService;
    @Autowired private AccountConverter accountConverter;

    @PostMapping("users/{id}/accounts")
    public void addAccountDetails(@RequestBody Account account,
                                     @PathVariable("id") Long userId){
        try{
            accountService.addAccountDetails(account, userId);
        }catch (APIException e){
            System.out.println("Account for user already exist.");
        }
    }

    @GetMapping("users/{id}/accounts")
    public AccountDTO getAccountDetails(@PathVariable("id") Long userId){

        Account account = accountService.getAccountDetails(userId);

        return accountConverter.accountToDTO(account);
    }

}
