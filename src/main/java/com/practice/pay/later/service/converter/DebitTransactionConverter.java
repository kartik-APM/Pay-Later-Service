package com.practice.pay.later.service.converter;


import com.practice.pay.later.service.dto.DebitTransactionDTO;
import com.practice.pay.later.service.model.DebitTransaction;
import org.springframework.stereotype.Component;

@Component
public class DebitTransactionConverter {

    public DebitTransaction DtoToDebitTransaction(DebitTransactionDTO debitTransactionDTO){

        DebitTransaction debitTransaction = new DebitTransaction();

        debitTransaction.setAmount(debitTransactionDTO.getAmount());
        debitTransaction.setOrderId(debitTransactionDTO.getOrderId());

        return debitTransaction;
    }





}
