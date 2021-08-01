package com.practice.pay.later.service.validation;

import org.springframework.stereotype.Component;

@Component
public class RepaymentTransactionValidation {

    public Boolean checkAmount(int authorisedLimit, int amountAfterRepayment){
        return amountAfterRepayment <= authorisedLimit;
    }
}
