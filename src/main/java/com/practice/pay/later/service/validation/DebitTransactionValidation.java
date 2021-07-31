package com.practice.pay.later.service.validation;


import com.practice.pay.later.service.dto.UserDTO;
import com.practice.pay.later.service.model.Account;
import org.springframework.stereotype.Component;

@Component
public class DebitTransactionValidation {

    public Boolean check(int currentAvailableLimit) {

        if (currentAvailableLimit < 0) {
            return false;
        }
        return true;
    }
}
