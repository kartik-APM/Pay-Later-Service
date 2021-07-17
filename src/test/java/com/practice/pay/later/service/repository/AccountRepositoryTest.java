package com.practice.pay.later.service.repository;

import com.practice.pay.later.service.model.Account;
import com.practice.pay.later.service.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;


@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void saveAccount(){

        Date date = new Date();

        User user = User.builder()
                .firstName("KKp")
                .emailId("kkpNEW.p@gmail.com")
                .build();

        Account account = Account.builder()
                .authorisedCreditLimit(30000)
                .availableCreditLimit(3000)
                .user(user)
                .build();

        accountRepository.save(account);
    }

    @Test
    public void printAllAccount(){
        List<Account> accounts =
                accountRepository.findAll();

        System.out.println("User List =" + accounts);
    }
}