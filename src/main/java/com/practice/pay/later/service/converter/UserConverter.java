package com.practice.pay.later.service.converter;

import com.practice.pay.later.service.dto.UserDTO;
import com.practice.pay.later.service.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
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

}
