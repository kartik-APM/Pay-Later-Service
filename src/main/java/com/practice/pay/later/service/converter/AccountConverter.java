package com.practice.pay.later.service.converter;


import com.practice.pay.later.service.dto.AccountDTO;
import com.practice.pay.later.service.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {

    public AccountDTO accountToDTO(Account account){
        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setUserID(account.getUser().getUserId());
        accountDTO.setAccountId(account.getAccountId());
        accountDTO.setAuthorisedCreditLimit(account.getAuthorisedCreditLimit());
        accountDTO.setAvailableCreditLimit(account.getAvailableCreditLimit());

        return accountDTO;
    }

    public Account DtoToAccount(AccountDTO accountDTO){

        Account account = new Account();

        account.setAuthorisedCreditLimit(accountDTO.getAuthorisedCreditLimit());
        account.setAvailableCreditLimit(accountDTO.getAuthorisedCreditLimit());

        return account;
    }
}
