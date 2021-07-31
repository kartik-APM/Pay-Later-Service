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
import com.practice.pay.later.service.validation.UserValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final UserValidation userValidation;

    @Autowired
    public UserServiceImpl(
            final UserRepository userRepository,
            final UserConverter userConverter,
            final UserValidation userValidation) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.userValidation = userValidation;
    }


    @Transactional
    public void saveUserData(final User user) {
        this.userRepository.save(user);
    }

    @Override
    public ApiResponse<String> addUser(UserDTO userDTO) {

        log.info("Creating User with EmailID {}", userDTO.getEmailId());
        ApiResponse<String> apiResponse = new ApiResponse<>();

        // Check if data input
        // is valid or not
        if (!userValidation.verifyUserData(userDTO)) {
            apiResponse.setStatus(Status.FAILURE);
            apiResponse.setMessage("Input Data is wrong.");
            return apiResponse;
        }

        // Check if User
        // already exist or not
        User user1 = this.userRepository.findByEmailId(userDTO.getEmailId());
        if (null != user1) {
            try {
                throw new SQLException("Email ID is already taken");
            } catch (SQLException e) {
                log.info(e.getMessage());
                apiResponse.setStatus(Status.FAILURE);
                apiResponse.setMessage(e.getMessage());
            }
        } else {
            User user = this.userConverter.DtoToUser(userDTO);
            saveUserData(user);
            apiResponse.setMessage("User Created Successfully");
            log.info("User created with EmailID {}", userDTO.getEmailId());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse<List<UserDTO>> getAllUser() throws NotFoundException {

        log.info("Fetching details of all the users");
        ApiResponse<List<UserDTO>> apiResponse = new ApiResponse<>();
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            try {
                throw new NotFoundException("No User Exist");
            } catch (NotFoundException e) {
                apiResponse.setMessage(e.getMessage());
            }
        } else {
            List<UserDTO> userDTOS = this.userConverter.userToDTOList(users);
            apiResponse.setData(userDTOS);
            apiResponse.setMessage("All users Fetched Successfully");
        }
        log.info("Fetched details for all users");
        return apiResponse;
    }

    @Override
    public ApiResponse<UserDTO> getUserById(Long userId) throws NotFoundException {

        log.info("Fetching details of user with userId {}", userId);
        ApiResponse<UserDTO> apiResponse = new ApiResponse<>();
        User userFromDb;

        try {
            userFromDb = this.userRepository.findById(userId).get();
        } catch (Exception e) {
            log.info(e.getMessage());
            apiResponse.setStatus(Status.FAILURE);
            apiResponse.setMessage("No user exist with userId " + userId);
            log.info("Processing Failed while Fetching User");
            return apiResponse;
        }

        UserDTO userDTO = userConverter.userToDTO(userFromDb);
        apiResponse.setData(userDTO);
        apiResponse.setMessage("User Fetched Successfully");
        log.info("Fetched details of user with userId {}", userId);
        return apiResponse;
    }

    @Override
    public List<User> getUserByFirstName(String firstName) throws NotFoundException {
        List<User> users = userRepository.findByFirstNameIgnoreCase(firstName);
        if (users.isEmpty()) throw new NotFoundException("");
        return users;
    }

    @Override
    public List<User> getUserByLastName(String lastName) throws NotFoundException {
        List<User> users = userRepository.findByLastNameIgnoreCase(lastName);
        if (users.isEmpty()) throw new NotFoundException();
        return users;
    }

    @Override
    public User getUserByEmailId(String emailId) throws NullPointerException {
        User user = userRepository.findByEmailId(emailId);
        if (user == null) throw new NullPointerException();
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
