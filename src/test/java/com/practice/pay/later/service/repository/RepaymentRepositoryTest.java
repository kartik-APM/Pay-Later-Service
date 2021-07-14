package com.practice.pay.later.service.repository;

import com.practice.pay.later.service.model.Account;
import com.practice.pay.later.service.model.Refund;
import com.practice.pay.later.service.model.Repayment;
import com.practice.pay.later.service.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RepaymentRepositoryTest {

    @Autowired
    private RepaymentRepository repaymentRepository;

    @Test
    public void saveRepaymentWithAccount(){
        User user = User.builder()
                .emailId("jhgj@hotmail.com")
                .build();

        Account account = Account.builder()
                .accountId("71123811010947")
                .user(user)
                .build();

        Date date = new Date();
        Repayment repayment = Repayment.builder()
                .repaymentTransactionId("4321141234")
                .amount(12340)
                .transactionDate(date)
                .status("OK! 200")
                .account(account)
                .build();

        repaymentRepository.save(repayment);
    }

}