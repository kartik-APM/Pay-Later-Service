package com.practice.pay.later.service.controller;


import com.practice.pay.later.service.model.DebitTransaction;
import com.practice.pay.later.service.model.Refund;
import com.practice.pay.later.service.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RefundController {


    @Autowired
    private RefundService refundService;

    @PostMapping("/users/{id}/accounts/{id2}/refund")
    public void addRefund(@RequestBody Refund refund,
                          @PathVariable("id") Long userId,
                          @PathVariable("id2") Long accountId){

        refundService.addRefund(refund, userId, accountId);

    }


    @GetMapping("/users/{id}/accounts/refund")
    public List<ArrayList> getAllRefunds(@PathVariable("id2") Long accountId){

        return refundService.getAllRefunds(accountId);

    }

}
