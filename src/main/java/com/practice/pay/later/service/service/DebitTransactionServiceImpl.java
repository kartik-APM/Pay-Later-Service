package com.practice.pay.later.service.service;


import com.practice.pay.later.service.model.Account;
import com.practice.pay.later.service.model.DebitTransaction;
import com.practice.pay.later.service.repository.AccountRepository;
import com.practice.pay.later.service.repository.DebitTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DebitTransactionServiceImpl implements DebitTransactionService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DebitTransactionRepository debitTransactionRepository;

    @Override
    public void addDebitTransaction(DebitTransaction debitTransaction,
                                    Long userId,
                                    Long accountId) {

        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate= DateFor.format(date);

        Account accountFromDb = accountRepository.findById(accountId).get();

        debitTransaction.setAccount(accountFromDb);
        debitTransaction.setTransactionDate(stringDate);

        int x = accountFromDb.getAvailableCreditLimit() - debitTransaction.getAmount();

        if(x<0){
            System.out.println("Invalid Operation.");
            debitTransaction.setStatus("Failed");
        }else {
            accountFromDb.setDateAccountUpdated(stringDate);
            accountFromDb.setAvailableCreditLimit(x);
            debitTransaction.setStatus("200 OK");
        }
        debitTransactionRepository.save(debitTransaction);
    }

    @Override
    public List<ArrayList> getAllDebitTransaction(Long accountId) {

        return debitTransactionRepository.getAllDebitTransaction(accountId);
    }

}
