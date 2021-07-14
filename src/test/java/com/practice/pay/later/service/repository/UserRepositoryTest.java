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
                .street("Goinganj")
                .city("Gkp")
                .state("U.P.")
                .pin("221010")
                .build();

        User user = User.builder()
                .emailId("abc146@gmail.com")
                .firstName("Harsh")
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