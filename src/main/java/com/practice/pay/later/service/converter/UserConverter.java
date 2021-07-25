package com.practice.pay.later.service.converter;

import com.practice.pay.later.service.dto.UserDTO;
import com.practice.pay.later.service.exception.NotFoundException;
import com.practice.pay.later.service.model.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserConverter {


    public UserDTO userToDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(user.getUserId());
        userDTO.setName(user.getFirstName() +" "+ user.getLastName());
        userDTO.setEmailId(user.getEmailId());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAddress(user.getAddress());

        return userDTO;
    }

    public List<UserDTO> userToDTOList(List<User> users){
        return users.stream().map(x -> userToDTO(x))
                .collect(Collectors.toList());

    }

    public User DtoToUser(UserDTO userDTO){
        User user = new User();
        String[] arrOfStr = userDTO.getName().split(" ");

        user.setFirstName(arrOfStr[0]);
        user.setLastName(arrOfStr[1]);
        user.setEmailId(userDTO.getEmailId());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());

        return user;
    }

}
