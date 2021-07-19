package com.practice.pay.later.service.service;


import com.practice.pay.later.service.model.Account;
import com.practice.pay.later.service.model.Repayment;
import com.practice.pay.later.service.repository.AccountRepository;
import com.practice.pay.later.service.repository.RepaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RepaymentServiceImpl implements RepaymentService {

    @Autowired
    private RepaymentRepository repaymentRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void addRepayment(Repayment repayment,
                             Long userId,
                             Long accountId) {

        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = DateFor.format(date);

        Account accountFromDb = accountRepository.findById(accountId).get();

        repayment.setAccount(accountFromDb);
        repayment.setTransactionDate(stringDate);
        accountFromDb.setDateAccountUpdated(stringDate);
        accountFromDb.setAvailableCreditLimit(repayment.getAmount()
                + accountFromDb.getAvailableCreditLimit());
        repayment.setStatus("200 OK");

        repaymentRepository.save(repayment);
    }

    @Override
    public List<ArrayList> getAllRepayment(Long accountId) {

        return repaymentRepository.getAllRepayments();
    }

}
