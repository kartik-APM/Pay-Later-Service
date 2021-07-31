package com.practice.pay.later.service.validation;

import com.practice.pay.later.service.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidation {

    /**
     * To check if EmailId is valid or not
     */
    public Boolean emailCheckFail(String email) {
        String regex =
                "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();
    }

    public Boolean verifyUserData(UserDTO userDTO){
        if(null == userDTO) return false;
        try {
            String[] arrOfStr = userDTO.getName().split(" ");
            if (null == arrOfStr[0]) return false;
            if (null == arrOfStr[1]) return false;
        }catch (ArrayIndexOutOfBoundsException e){
            return false;
        }
        if(null == userDTO.getEmailId())    return false;
        return !emailCheckFail(userDTO.getEmailId());
    }
}
