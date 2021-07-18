package com.practice.pay.later.service.controller;


import com.practice.pay.later.service.model.DebitTransaction;
import com.practice.pay.later.service.model.Refund;
import com.practice.pay.later.service.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
