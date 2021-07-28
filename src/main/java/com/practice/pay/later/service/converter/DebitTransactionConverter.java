package com.practice.pay.later.service.converter;


import com.practice.pay.later.service.dto.DebitTransactionDTO;
import com.practice.pay.later.service.model.DebitTransaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DebitTransactionConverter {

    public DebitTransaction DtoToDebitTransaction(DebitTransactionDTO debitTransactionDTO){
        DebitTransaction debitTransaction = new DebitTransaction();

        debitTransaction.setAmount(debitTransactionDTO.getAmount());
        debitTransaction.setOrderId(debitTransactionDTO.getOrderId());

        return debitTransaction;
    }

    public DebitTransactionDTO arrayListToDto(ArrayList<?> arrayList){
        DebitTransactionDTO debitTransactionDTO = new DebitTransactionDTO();

        Long transactionId = (Long) arrayList.get(0);
        debitTransactionDTO.setDebitTransactionId(transactionId);

        int amountDebited = (int) arrayList.get(1);
        debitTransactionDTO.setAmount(amountDebited);

        String orderId = (String) arrayList.get(2);
        debitTransactionDTO.setOrderId(orderId);

        Date date = (Date) arrayList.get(3);
        debitTransactionDTO.setTransactionDate(date);

        return debitTransactionDTO;
    }

    public List<DebitTransactionDTO> arrayListToDTOList(List<ArrayList> arrayLists){
        return arrayLists.stream().map(x -> arrayListToDto(x)).
                collect(Collectors.toList());
    }

}
