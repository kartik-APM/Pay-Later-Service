package com.practice.pay.later.service.service;


import com.practice.pay.later.service.model.User;

import java.util.List;

public interface UserService {

    public User addUser(User user);

    public List<User> getAllUser();

    public User getUser(Long userId);

    public List<User> getUserByFirstName(String firstName);

    public List<User> getUserByLastName(String lastName);

    public User getUserByEmailId(String emailId);

    public User updateUserDetails(User user, Long userId);

    public void updateAddressDetails(User user, Long userId);

//    public void updateAccountDetails(User user, Long userId);

}
