package com.practice.pay.later.service.dto;


import lombok.Data;

import java.util.Date;

@Data
public class DebitTransactionDTO {

    private Long debitTransactionId;
    private int amount;
    private String orderId;
    private Date transactionDate;

}
