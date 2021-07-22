package com.practice.pay.later.service.controller;


import com.practice.pay.later.service.converter.AccountConverter;
import com.practice.pay.later.service.dto.AccountDTO;
import com.practice.pay.later.service.model.Account;
import com.practice.pay.later.service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AccountController {

    @Autowired private AccountService accountService;
    @Autowired private AccountConverter accountConverter;

    @PostMapping("users/{id}/accounts")
    public void addAccountDetails(@RequestBody Account account,
                                     @PathVariable("id") Long userId){
        accountService.addAccountDetails(account, userId);

    }

    @GetMapping("users/{id}/accounts")
    public AccountDTO getAccountDetails(@PathVariable("id") Long userId){

        Account account = accountService.getAccountDetails(userId);

        return accountConverter.accountToDTO(account);
    }

}
