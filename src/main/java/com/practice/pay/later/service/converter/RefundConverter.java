package com.practice.pay.later.service.converter;

import com.practice.pay.later.service.dto.RefundDTO;
import com.practice.pay.later.service.model.Refund;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;

@Component
public class RefundConverter {

    public Refund DtoToRefund(@NotNull RefundDTO refundDTO){
        Refund refund = new Refund();

        refund.setAmount(refundDTO.getAmount());
        refund.setOriginalTransactionId(refundDTO.getOriginalTransactionId());

        return refund;
    }


}
