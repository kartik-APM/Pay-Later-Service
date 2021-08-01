package com.practice.pay.later.service.controller;


import com.practice.pay.later.service.dto.RepaymentDTO;
import com.practice.pay.later.service.exception.ApiResponse;
import com.practice.pay.later.service.model.Refund;
import com.practice.pay.later.service.model.Repayment;
import com.practice.pay.later.service.service.RepaymentService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RepaymentController {

    @Autowired
    private RepaymentService repaymentService;

    @PostMapping("/users/{id}/accounts/repayment")
    ResponseEntity<ApiResponse<String>> processRepaymentTransaction(
            @RequestBody @NotNull RepaymentDTO repaymentDTO,
            @PathVariable("id") @NotNull Long userId) {

        return new ResponseEntity<>(
                repaymentService.processRepaymentTransaction(repaymentDTO, userId),
                HttpStatus.OK);
    }

    @GetMapping("/users/{id}/accounts/repayment")
    public List<ArrayList> getAllRepayment(@PathVariable("id2") Long accountId) {

        return repaymentService.getAllRepayment(accountId);
    }


}
