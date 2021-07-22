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

    @Autowired private AccountRepository accountRepository;
    @Autowired private UserRepository userRepository;

    @Test
    public void saveAccount(){

        User user =
                userRepository.findById(1L).get();

        Account account = Account.builder()
                .authorisedCreditLimit(30000)
                .dateAccountCreated(new Date())
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