package com.practice.pay.later.service.service;


import com.practice.pay.later.service.exception.NotFoundException;
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
    public List<User> getAllUser() { return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) throws NotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with UserId: "+ userId));
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

    public void updateFirstName(User user, String str) {
        if (Objects.nonNull(str) &&
                !"".equalsIgnoreCase(str)) {
            user.setFirstName(str);
        }
    }
    public void updateLastName(User user, String str) {
        if (Objects.nonNull(str) &&
                !"".equalsIgnoreCase(str)) {
            user.setLastName(str);
        }
    }
    public void updatePhoneNumber(User user, String str) {
        if (Objects.nonNull(str) &&
                !"".equalsIgnoreCase(str)) {
            user.setPhoneNumber(str);
        }
    }
    @Override
    public void updateUserDetails(User user, Long userId) {
        User userFromDb = userRepository.findById(userId).get();
        String str;

        str = user.getFirstName();
        updateFirstName(userFromDb, str);

        str = user.getLastName();
        updateLastName(userFromDb, str);

        str = user.getPhoneNumber();
        updatePhoneNumber(userFromDb, str);

        userRepository.save(userFromDb);
    }

    public void updateStreet(Address address, String str) {
        if (Objects.nonNull(str) &&
                !"".equalsIgnoreCase(str)) {
            address.setStreet(str);
        }
    }
    public void updateCity(Address address, String str) {
        if (Objects.nonNull(str) &&
                !"".equalsIgnoreCase(str)) {
            address.setCity(str);
        }
    }
    public void updateState(Address address, String str) {
        if (Objects.nonNull(str) &&
                !"".equalsIgnoreCase(str)) {
            address.setState(str);
        }
    }
    public void updatePin(Address address, String str) {
        if (Objects.nonNull(str) &&
                !"".equalsIgnoreCase(str)) {
            address.setPin(str);
        }
    }
    @Override
    public void updateAddressDetails(User user, Long userId) {
        User userFromDb = userRepository.findById(userId).get();
        Address address = userFromDb.getAddress();
        String str;

        str = user.getAddress().getStreet();
        updateStreet(address, str);

        str = user.getAddress().getCity();
        updateCity(address, str);

        str = user.getAddress().getState();
        updateState(address, str);

        str = user.getAddress().getPin();
        updatePin(address, str);

        userRepository.save(userFromDb);
    }

}
