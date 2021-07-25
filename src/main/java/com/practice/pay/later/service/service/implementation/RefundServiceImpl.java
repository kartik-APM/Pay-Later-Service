package com.practice.pay.later.service.service.implementation;


import com.practice.pay.later.service.enums.Status;
import com.practice.pay.later.service.model.Account;
import com.practice.pay.later.service.model.Refund;
import com.practice.pay.later.service.repository.AccountRepository;
import com.practice.pay.later.service.repository.RefundRepository;
import com.practice.pay.later.service.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RefundServiceImpl implements RefundService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RefundRepository refundRepository;


    @Override
    public void addRefund(Refund refund,
                          Long userId,
                          Long accountId) {

        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate= DateFor.format(date);

        Account accountFromDb = accountRepository.findById(accountId).get();

        refund.setAccount(accountFromDb);
        refund.setTransactionDate(date);
        refund.setStatus(Status.SUCCESSFUL);

        int x = accountFromDb.getAuthorisedCreditLimit();
        int newAmount = accountFromDb.getAvailableCreditLimit() + refund.getAmount();

        if(newAmount > x){
            accountFromDb.setAvailableCreditLimit(x);
        }else {
            accountFromDb.setAvailableCreditLimit(newAmount);
        }
        refundRepository.save(refund);
    }

    @Override
    public List<ArrayList> getAllRefunds(Long accountId) {

        return refundRepository.getAllRefunds();
    }

}
