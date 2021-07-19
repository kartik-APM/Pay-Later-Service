package com.practice.pay.later.service.controller;


import com.practice.pay.later.service.model.DebitTransaction;
import com.practice.pay.later.service.service.DebitTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DebitTransactionController {

    @Autowired
    private DebitTransactionService debitTransactionService;

    @PostMapping("/users/{id}/accounts/{id2}/debit")
    public void addDebitTransaction(@RequestBody DebitTransaction debitTransaction,
                                    @PathVariable("id") Long userId,
                                    @PathVariable("id2") Long accountId) {

        debitTransactionService.addDebitTransaction(debitTransaction, userId, accountId);
    }

    @GetMapping("/users/{id}/accounts/{id2}/debit")
    public List<ArrayList> getAllDebitTransaction(@PathVariable("id2") Long accountId) {

        return debitTransactionService.getAllDebitTransaction(accountId);
    }

}
