package com.practice.pay.later.service.controller;

import com.practice.pay.later.service.converter.UserConverter;
import com.practice.pay.later.service.dto.UserDTO;
import com.practice.pay.later.service.enums.Status;
import com.practice.pay.later.service.exception.ApiResponse;
import com.practice.pay.later.service.exception.NotFoundException;
import com.practice.pay.later.service.model.User;
import com.practice.pay.later.service.service.UserService;
import com.sun.istack.NotNull;
import io.swagger.annotations.Api;
import org.hibernate.annotations.NotFound;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;


    @PostMapping("/users")
    ResponseEntity<ApiResponse<String>> addNewUser(@RequestBody @NotNull UserDTO userDTO) {
        return new ResponseEntity<ApiResponse<String>>(userService.addUser(userDTO),
                HttpStatus.OK);
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiResponse<List<UserDTO>>> getAllUser() {
        return new ResponseEntity<ApiResponse<List<UserDTO>>>(userService.getAllUser(),
                HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable("id") @NotNull Long userId) {
        ApiResponse<UserDTO> apiResponse = new ApiResponse<>();

        try {
            User user = userService.getUserById(userId);
            UserDTO userDTO = userConverter.userToDTO(user);
            apiResponse.setData(userDTO);
            //ApiResponse<UserDTO> apiResponse1 =  (ApiResponse<UserDTO>) ApiResponse.builder().data(userDTO).build();
            return new ResponseEntity<ApiResponse<UserDTO>>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException e) {
            apiResponse.setMessage("No User exist with User ID " + userId);
        } catch (Exception e) {
            apiResponse.setStatus(Status.FAILURE);
        }

        return new ResponseEntity<ApiResponse<UserDTO>>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/firstName/{id}")
    ResponseEntity<ApiResponse<List<UserDTO>>> getUserByFirstName(@PathVariable("id") String firstName) {
        ApiResponse<List<UserDTO>> apiResponse = new ApiResponse<>();

        try {
            List<User> users = userService.getUserByFirstName(firstName);
            List<UserDTO> userDTOS = userConverter.userToDTOList(users);
            apiResponse.setData(userDTOS);
            return new ResponseEntity<ApiResponse<List<UserDTO>>>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException e) {
            apiResponse.setMessage("No User exist with User First Name " + firstName);
        } catch (Exception e) {
            apiResponse.setStatus(Status.FAILURE);
        }

        return new ResponseEntity<ApiResponse<List<UserDTO>>>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/lastName/{id}")
    ResponseEntity<ApiResponse<List<UserDTO>>> getUserByLastName(@PathVariable("id") String lastName) {
        ApiResponse<List<UserDTO>> apiResponse = new ApiResponse<>();

        try {
            List<User> users = userService.getUserByLastName(lastName);
            List<UserDTO> userDTOS = userConverter.userToDTOList(users);
            apiResponse.setData(userDTOS);
            return new ResponseEntity<ApiResponse<List<UserDTO>>>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException e) {
            apiResponse.setMessage("No User exist with Last Name " + lastName);
        } catch (Exception e) {
            apiResponse.setStatus(Status.FAILURE);
        }

        return new ResponseEntity<ApiResponse<List<UserDTO>>>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/emailId/{id}")
    ResponseEntity<ApiResponse<UserDTO>> getUserByEmailId(@PathVariable("id") String emailId) {
        ApiResponse<UserDTO> apiResponse = new ApiResponse<>();
        try {
            User user = userService.getUserByEmailId(emailId);
            UserDTO userDTO = userConverter.userToDTO(user);
            apiResponse.setData(userDTO);
            return new ResponseEntity<ApiResponse<UserDTO>>(apiResponse, HttpStatus.OK);
        } catch (NullPointerException e) {
            apiResponse.setMessage("No User exist with email-ID " + emailId);
        } catch (Exception e) {
            apiResponse.setStatus(Status.FAILURE);
        }

        return new ResponseEntity<ApiResponse<UserDTO>>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/users/{id}")
    public void updateUserDetails(@RequestBody User user,
                                  @PathVariable("id") Long userId) {
        userService.updateUserDetails(user, userId);
    }

    @PutMapping("/users/{id}/address")
    public void updateAddressDetails(@RequestBody User user,
                                     @PathVariable("id") Long userId) {
        userService.updateAddressDetails(user, userId);
    }
}