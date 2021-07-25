package com.practice.pay.later.service.controller;


import com.practice.pay.later.service.model.DebitTransaction;
import com.practice.pay.later.service.service.DebitTransactionService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DebitTransactionController {

    @Autowired
    private DebitTransactionService debitTransactionService;

    //DO not use entity
    //USE DTO

    @PostMapping("/users/{id}/accounts/{id2}/debit")
    public void addDebitTransaction(@RequestBody DebitTransaction debitTransaction,
                                    @PathVariable("id") @NotNull Long userId,
                                    @PathVariable("id2") @NotNull Long accountId) {

        debitTransactionService.addDebitTransaction(debitTransaction, userId, accountId);
    }

//    @GetMapping("/users/{id}/accounts/{id2}/debit")
//    public List<ArrayList> getAllDebitTransaction(@PathVariable("id2") Long accountId) {
//
//        return debitTransactionService.getAllDebitTransaction(accountId);
//    }

//    @GetMapping("/users/{id}/accounts/{id2}/debit")
//    public List<DebitTransaction> getAllDebitTransaction(@PathVariable("id2") Long accountId) {
//
//        return debitTransactionService.allTransaction(accountId);
//    }

}
