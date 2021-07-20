package com.practice.pay.later.service.converter;


import com.practice.pay.later.service.dto.AccountDTO;
import com.practice.pay.later.service.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {

    public AccountDTO accountToDTO(Account account){
        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setAccountId(account.getAccountId());
        accountDTO.setAuthorisedCreditLimit(account.getAuthorisedCreditLimit());
        accountDTO.setAvailableCreditLimit(account.getAvailableCreditLimit());

        return accountDTO;
    }
}
