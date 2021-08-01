package com.practice.pay.later.service.controller;


import com.practice.pay.later.service.dto.DebitTransactionDTO;
import com.practice.pay.later.service.exception.ApiResponse;
import com.practice.pay.later.service.model.DebitTransaction;
import com.practice.pay.later.service.service.DebitTransactionService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DebitTransactionController {

    @Autowired
    private DebitTransactionService debitTransactionService;

    @PostMapping("/users/{id}/accounts/debit")
    ResponseEntity<ApiResponse<String>> processDebitTransaction(
            @RequestBody @NotNull DebitTransactionDTO debitTransactionDTO,
            @PathVariable("id") @NotNull Long userId) {

        return new ResponseEntity<>(
                debitTransactionService.processDebitTransaction(debitTransactionDTO, userId),
                HttpStatus.OK);
    }

    @GetMapping("/users/{id}/accounts/debit")
    ResponseEntity<ApiResponse<List<DebitTransactionDTO>>> getAllDebitTransactionDetail(
            @PathVariable("id") Long userId) {

        return new ResponseEntity<>(
                debitTransactionService.getAllDebitTransaction(userId),
                HttpStatus.OK);
    }

}
