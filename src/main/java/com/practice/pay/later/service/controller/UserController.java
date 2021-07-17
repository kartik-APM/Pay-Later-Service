package com.practice.pay.later.service.controller;


import com.practice.pay.later.service.model.User;
import com.practice.pay.later.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") Long userId){

        return userService.getUserById(userId);
    }

    @GetMapping("/users/firstName/{id}")
    public List<User> getUserByFirstName(@PathVariable("id") String firstName){
        return userService.getUserByFirstName(firstName);
    }

    @GetMapping("/users/lastName/{id}")
    public List<User> getUserByLastName(@PathVariable("id") String lastName){
        return userService.getUserByLastName(lastName);
    }
    @GetMapping("/users/emailId/{id}")
    public User getUserByEmailId(@PathVariable("id") String emailId){
        return userService.getUserByEmailId(emailId);
    }

    @PutMapping("/users/{id}")
    public void updateUserDetails(@RequestBody User user,
                                  @PathVariable("id") Long userId){
        userService.updateUserDetails(user, userId);
    }

    @PutMapping("/users/{id}/address")
    public void updateAddressDetails(@RequestBody User user,
                                     @PathVariable("id") Long userId){
        userService.updateAddressDetails(user, userId);
    }
}
