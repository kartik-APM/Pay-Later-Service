package com.practice.pay.later.service.service.implementation;

import com.practice.pay.later.service.converter.AccountConverter;
import com.practice.pay.later.service.dto.AccountDTO;
import com.practice.pay.later.service.enums.Status;
import com.practice.pay.later.service.exception.ApiResponse;
import com.practice.pay.later.service.exception.NotFoundException;
import com.practice.pay.later.service.model.Account;
import com.practice.pay.later.service.model.User;
import com.practice.pay.later.service.repository.AccountRepository;
import com.practice.pay.later.service.repository.UserRepository;
import com.practice.pay.later.service.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountConverter accountConverter;

    @Override
    public ApiResponse<String> addAccountDetails(AccountDTO accountDTO,
                                                 Long userId) {

        log.info("Creating Account of User with UserID {}", userId);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        User userFromDb = this.userRepository.findById(userId).get();
        if (null == userFromDb) {
            try {
                throw new NotFoundException("No user exist.");
            } catch (NotFoundException e) {
                log.info(e.getMessage());
                apiResponse.setStatus(Status.FAILURE);
                apiResponse.setMessage(e.getMessage());
            }
        } else {
            accountDTO.setUserID(userId);
            Account account = this.accountConverter.DtoToAccount(accountDTO);
            account.setUser(userFromDb);
            this.accountRepository.save(account);
            apiResponse.setMessage("Account Created Successfully");
            log.info("Account Created of User with UserID {}", userId);
        }

        return apiResponse;

    }

    @Override
    public Account getAccountDetails(Long userId) {
        User userFromDb = userRepository.findById(userId).orElseThrow(()
                -> new NotFoundException("User not found with UserId: " + userId));
        Account account = userFromDb.getAccount();
        return account;
    }

}
