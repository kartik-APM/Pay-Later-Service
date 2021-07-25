package com.practice.pay.later.service.service.implementation;

import com.practice.pay.later.service.converter.UserConverter;
import com.practice.pay.later.service.dto.UserDTO;
import com.practice.pay.later.service.enums.Status;
import com.practice.pay.later.service.exception.ApiResponse;
import com.practice.pay.later.service.exception.NotFoundException;
import com.practice.pay.later.service.model.Address;
import com.practice.pay.later.service.model.User;
import com.practice.pay.later.service.repository.UserRepository;
import com.practice.pay.later.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private UserConverter userConverter;


    @Override
    public ApiResponse<String> addUser(UserDTO userDTO) {

        log.info("Creating User with EmailID {}",userDTO.getEmailId());
        ApiResponse<String> apiResponse = new ApiResponse<>();
        User user1 = this.userRepository.findByEmailId(userDTO.getEmailId());

        if(null != user1){
            try {
                throw new SQLException("Email ID is already taken.");
            } catch (SQLException e) {
                log.info(e.getMessage());
                apiResponse.setStatus(Status.FAILURE);
                apiResponse.setMessage(e.getMessage());
            }
        }else{
            User user = this.userConverter.DtoToUser(userDTO);
            this.userRepository.save(user);
            apiResponse.setMessage("User Created Successfully");
            log.info("User created with EmailID {}",userDTO.getEmailId());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse<List<UserDTO>> getAllUser() throws NotFoundException{

        log.info("Fetching details of all the users.");
        ApiResponse<List<UserDTO>> apiResponse = new ApiResponse<>();
        List<User> users = userRepository.findAll();

        if(users.isEmpty()) {
            try{
                throw new NotFoundException("No User Exist");
            } catch (NotFoundException e){
                apiResponse.setMessage(e.getMessage());
            }
        }else {
            List<UserDTO> userDTOS = this.userConverter.userToDTOList(users);
            apiResponse.setData(userDTOS);
            apiResponse.setMessage("All users Fetched Successfully");
        }
        return apiResponse;
    }

    @Override
    public User getUserById(Long userId) throws NotFoundException {
        log.info("Starting user Service Implementation for {}",userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with UserId: "+ userId));
    }

    @Override
    public List<User> getUserByFirstName(String firstName) throws NotFoundException{
        List<User> users = userRepository.findByFirstNameIgnoreCase(firstName);
        if(users.isEmpty())     throw new NotFoundException("");
        return users;
    }

    @Override
    public List<User> getUserByLastName(String lastName) throws NotFoundException{
        List<User> users = userRepository.findByLastNameIgnoreCase(lastName);
        if(users.isEmpty())     throw new NotFoundException();
        return users;
    }

    @Override
    public User getUserByEmailId(String emailId) throws NullPointerException{
        User user = userRepository.findByEmailId(emailId);
        if(user == null)        throw new NullPointerException();
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
