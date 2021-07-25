package com.practice.pay.later.service.repository;

import com.practice.pay.later.service.enums.Status;
import com.practice.pay.later.service.model.Account;
import com.practice.pay.later.service.model.Refund;
import com.practice.pay.later.service.model.Repayment;
import com.practice.pay.later.service.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

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
                .user(user)
                .build();

        Repayment repayment = Repayment.builder()
                .amount(12340)
                .status(Status.SUCCESSFUL)
                .account(account)
                .build();

        repaymentRepository.save(repayment);
    }

}