package com.practice.pay.later.service.controller;


import com.practice.pay.later.service.model.Refund;
import com.practice.pay.later.service.model.Repayment;
import com.practice.pay.later.service.service.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RepaymentController {

    @Autowired
    private RepaymentService repaymentService;

    @PostMapping("/users/{id}/accounts/{id2}/repayment")
    public void addRepayment(@RequestBody Repayment repayment,
                          @PathVariable("id") Long userId,
                          @PathVariable("id2") Long accountId) {

        repaymentService.addRepayment(repayment, userId, accountId);
    }

    @GetMapping("/users/{id}/accounts/{id2}/repayment")
    public List<ArrayList> getAllRepayment(@PathVariable("id2") Long accountId) {

        return repaymentService.getAllRepayment(accountId);
    }


}
