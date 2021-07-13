package com.practice.pay.later.service.repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.practice.pay.later.service.model.Address;
import com.practice.pay.later.service.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser(){
        Address address = Address.builder()
                .street("Goshainganj")
                .city("Gkp")
                .state("U.P.")
                .pin("220912")
                .build();

        User user = User.builder()
                .emailId("abc6@gmail.com")
                .firstName("Harshita")
                .phoneNumber("7348906723")
                .lastName("Pandey")
                .address(address)
                .build();

        userRepository.save(user);
    }

    @Test
    public void printAllUser(){
        List<User> users =
                userRepository.findAll();

        System.out.println("User List =" + users);
    }
}