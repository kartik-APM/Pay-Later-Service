package com.practice.pay.later.service.controller;


import com.practice.pay.later.service.converter.UserConverter;
import com.practice.pay.later.service.dto.UserDTO;
import com.practice.pay.later.service.model.User;
import com.practice.pay.later.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class UserController {

    @Autowired private UserService userService;
    @Autowired private UserConverter userConverter;

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUser(){

        List<User> users = userService.getAllUser();

        return userConverter.userToDTOList(users);
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable("id") Long userId){

        User user = userService.getUserById(userId);

        return userConverter.userToDTO(user);
    }

    @GetMapping("/users/firstName/{id}")
    public List<UserDTO> getUserByFirstName(@PathVariable("id") String firstName){

        List<User> users = userService.getUserByFirstName(firstName);

        return userConverter.userToDTOList(users);
    }

    @GetMapping("/users/lastName/{id}")
    public List<UserDTO> getUserByLastName(@PathVariable("id") String lastName){
        List<User> users = userService.getUserByLastName(lastName);

        return userConverter.userToDTOList(users);
    }
    @GetMapping("/users/emailId/{id}")
    public UserDTO getUserByEmailId(@PathVariable("id") String emailId){

        User user = userService.getUserByEmailId(emailId);

        return userConverter.userToDTO(user);
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
