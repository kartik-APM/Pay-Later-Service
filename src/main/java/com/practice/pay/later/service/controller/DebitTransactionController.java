package com.practice.pay.later.service.controller;


import com.practice.pay.later.service.service.DebitTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebitTransactionController {

    @Autowired
    private DebitTransactionService debitTransactionService;




}
