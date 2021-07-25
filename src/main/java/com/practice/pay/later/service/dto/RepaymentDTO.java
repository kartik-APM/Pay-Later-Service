package com.practice.pay.later.service.dto;


import lombok.Data;

import java.util.Date;

@Data
public class RepaymentDTO {

    private Long repaymentTransactionId;
    private int amount;
    private Date transactionDate;

}
