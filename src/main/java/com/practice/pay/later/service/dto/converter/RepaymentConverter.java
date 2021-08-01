package com.practice.pay.later.service.dto.converter;


import com.practice.pay.later.service.dto.RepaymentDTO;
import com.practice.pay.later.service.model.Repayment;
import org.springframework.stereotype.Component;

@Component
public class RepaymentConverter {

    public Repayment DtoToRepaymentTransaction(RepaymentDTO repaymentDTO){
        Repayment repayment = new Repayment();

        repayment.setAmount(repaymentDTO.getAmount());

        return repayment;
    }
}
