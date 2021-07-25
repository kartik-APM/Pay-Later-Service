package com.practice.pay.later.service.service.implementation;

import com.practice.pay.later.service.enums.Status;
import com.practice.pay.later.service.model.Account;
import com.practice.pay.later.service.model.DebitTransaction;
import com.practice.pay.later.service.repository.AccountRepository;
import com.practice.pay.later.service.repository.DebitTransactionRepository;
import com.practice.pay.later.service.service.DebitTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DebitTransactionServiceImpl implements DebitTransactionService {

    @Autowired private AccountRepository accountRepository;

    @Autowired private DebitTransactionRepository debitTransactionRepository;

    @Override
    //@Transactional
    //Process debit transaction FUN NAME
    public void addDebitTransaction(DebitTransaction debitTransaction,
                                    Long userId,
                                    Long accountId) {

        Account accountFromDb = accountRepository.findById(accountId).get();

        //Null pointer check
        //try catch

        debitTransaction.setAccount(accountFromDb);

        int x = accountFromDb.getAvailableCreditLimit() - debitTransaction.getAmount();

        if(x<0){
            debitTransaction.setStatus(Status.FAILURE);
        }else {
            accountFromDb.setAvailableCreditLimit(x);
            debitTransaction.setStatus(Status.SUCCESSFUL);
        }

        debitTransactionRepository.save(debitTransaction);
    }

//    @Override
//    public List<ArrayList> getAllDebitTransaction(Long accountId) {
//
//        return debitTransactionRepository.getAllDebitTransaction(accountId);
//    }

//    @Override
//    public List<DebitTransaction> allTransaction(Long accountId) {
//
//        return debitTransactionRepository.allTransactions(accountId);
//    }
}
