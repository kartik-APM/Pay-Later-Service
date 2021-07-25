package com.practice.pay.later.service.dto;


import lombok.Data;

import java.util.Date;

@Data
public class RefundDTO {

    private Long repaymentTransactionId;
    private int amount;
    private Long originalTransactionId;
    private Date transactionDate;

}
