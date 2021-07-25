package com.practice.pay.later.service.controller;


import com.practice.pay.later.service.converter.AccountConverter;
import com.practice.pay.later.service.dto.AccountDTO;
import com.practice.pay.later.service.exception.ApiResponse;
import com.practice.pay.later.service.model.Account;
import com.practice.pay.later.service.service.AccountService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountConverter accountConverter;

    @PostMapping("users/{id}/accounts")
    ResponseEntity<ApiResponse<String>> addAccountDetails(@RequestBody @NotNull AccountDTO accountDTO,
                                                          @PathVariable("id") @NotNull Long userId) {
        return new ResponseEntity<ApiResponse<String>>(
                accountService.addAccountDetails(accountDTO, userId),
                HttpStatus.OK);
    }

    @GetMapping("users/{id}/accounts")
    public AccountDTO getAccountDetails(@PathVariable("id") Long userId) {

        Account account = accountService.getAccountDetails(userId);

        return accountConverter.accountToDTO(account);
    }

}
