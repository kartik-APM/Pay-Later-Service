package com.practice.pay.later.service.service;


import com.practice.pay.later.service.model.Address;
import com.practice.pay.later.service.model.User;
import com.practice.pay.later.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> getUserByFirstName(String firstName) {
        return userRepository.findByFirstNameIgnoreCase(firstName);
    }

    @Override
    public List<User> getUserByLastName(String lastName) {
        return userRepository.findByLastNameIgnoreCase(lastName);
    }

    @Override
    public User getUserByEmailId(String emailId) {
        return userRepository.findByEmailId(emailId);
    }

    @Override
    public void updateAddressDetails(User user, Long userId) {
        User userFromDb = userRepository.findById(userId).get();
        Address address = userFromDb.getAddress();

        if (Objects.nonNull(user.getAddress().getStreet()) &&
                !"".equalsIgnoreCase(user.getAddress().getStreet())) {
            address.setStreet(user.getAddress().getStreet());
        }

        if (Objects.nonNull(user.getAddress().getCity()) &&
                !"".equalsIgnoreCase(user.getAddress().getCity())) {
            address.setCity(user.getAddress().getCity());
        }

        if (Objects.nonNull(user.getAddress().getState()) &&
                !"".equalsIgnoreCase(user.getAddress().getState())) {
            address.setState(user.getAddress().getState());
        }

        if (Objects.nonNull(user.getAddress().getPin()) &&
                !"".equalsIgnoreCase(user.getAddress().getPin())) {
            address.setPin(user.getAddress().getPin());
        }

        userRepository.save(userFromDb);

    }

    @Override
    public User updateUserDetails(User user, Long userId) {
        User userFromDb = userRepository.findById(userId).get();

        if (Objects.nonNull(user.getFirstName()) &&
                !"".equalsIgnoreCase(user.getFirstName())) {
            userFromDb.setFirstName(user.getFirstName());
        }

        if (Objects.nonNull(user.getLastName()) &&
                !"".equalsIgnoreCase(user.getLastName())) {
            userFromDb.setLastName(user.getLastName());
        }

        if (Objects.nonNull(user.getPhoneNumber()) &&
                !"".equalsIgnoreCase(user.getPhoneNumber())) {
            userFromDb.setPhoneNumber(user.getPhoneNumber());
        }

        return userRepository.save(userFromDb);
    }

//    @Override
//    public void updateAccountDetails(User user, Long userId) {
//        User userFromDb = userRepository.findById(userId).get();
//        Account account = userFromDb.getAccount();
//
//        if (Objects.nonNull(user.getAccount().getAccountId()) &&
//                !"".equalsIgnoreCase(user.getAccount().getAccountId())) {
//            account.setAccountId(user.getAccount().getAccountId());
//        }
//
//        userRepository.save(userFromDb);
//
//    }

}
