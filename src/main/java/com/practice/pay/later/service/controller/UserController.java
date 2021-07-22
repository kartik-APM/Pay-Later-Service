package com.practice.pay.later.service.controller;


import com.practice.pay.later.service.converter.UserConverter;
import com.practice.pay.later.service.dto.UserDTO;
import com.practice.pay.later.service.exception.ApiResponse;
import com.practice.pay.later.service.exception.NotFoundException;
import com.practice.pay.later.service.model.User;
import com.practice.pay.later.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class UserController {

    @Autowired private UserService userService;
    @Autowired private UserConverter userConverter;

    @PostMapping("/users")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiResponse<List<UserDTO>>> getAllUser(){
        ApiResponse<List<UserDTO>> apiResponse= new ApiResponse<>();

        try {
            List<User> users = userService.getAllUser();
            List<UserDTO> userDTOS = userConverter.userToDTOList(users);
            apiResponse.setData(userDTOS);
            return new ResponseEntity<ApiResponse<List<UserDTO>>>(apiResponse,HttpStatus.OK);
        }catch (NotFoundException e){

        }

        apiResponse.setMessage("User not found");
        apiResponse.setStatus("Fail");
        return new ResponseEntity<ApiResponse<List<UserDTO>>>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/users/{id}")
    ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable("id") Long userId){
        ApiResponse<UserDTO> apiResponse= new ApiResponse<>();

        try {
            User user = userService.getUserById(userId);
            UserDTO userDTO = userConverter.userToDTO(user);
            apiResponse.setData(userDTO);
            return new ResponseEntity<ApiResponse<UserDTO>>(apiResponse,HttpStatus.OK);
        }catch (NotFoundException e){

        }

        apiResponse.setMessage("User not found");
        apiResponse.setStatus("Fail");
        return new ResponseEntity<ApiResponse<UserDTO>>(apiResponse,HttpStatus.NOT_FOUND);
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
