package com.practice.pay.later.service.validation;

import org.springframework.stereotype.Component;

@Component
public class DebitTransactionValidation {

    public Boolean check(int currentAvailableLimit) {
        return currentAvailableLimit >= 0;
    }

}
