package com.practice.pay.later.service.dto;


import lombok.Data;

@Data
public class AccountDTO {

    private Long userID;
    private Long accountId;
    private int authorisedCreditLimit;
    private int availableCreditLimit;

}
