package com.practice.pay.later.service.service.implementation;

import com.practice.pay.later.service.dto.converter.AccountConverter;
import com.practice.pay.later.service.dto.AccountDTO;
import com.practice.pay.later.service.enums.Status;
import com.practice.pay.later.service.exception.ApiResponse;
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


    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final AccountConverter accountConverter;
    @Autowired
    public AccountServiceImpl(
            final UserRepository userRepository,
            final AccountRepository accountRepository,
            final AccountConverter accountConverter){
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.accountConverter = accountConverter;
    }

    @Override
    /*
     Function To add Account
     Details to the User
     */
    public ApiResponse<String> addAccountDetails(
            AccountDTO accountDTO,
            Long userId) throws IllegalStateException{

        log.info("Creating Account of User with UserID {}", userId);

        ApiResponse<String> apiResponse = new ApiResponse<>();
        User userFromDb;

        // Check for Valid User
        try {
            userFromDb = this.userRepository.findById(userId).get();
        } catch (Exception e) {
            apiResponse.setStatus(Status.FAILURE);
            apiResponse.setMessage("No User exist with userId " + userId);
            log.info("No User exist with userId {}", userId);
            return apiResponse;
        }

        // Check if user already
        // have an account
        try{
            Account account = userFromDb.getAccount();
            if(null != account){
                throw new IllegalStateException("Account already exist for the user");
            }
        }catch (IllegalStateException e){
            apiResponse.setStatus(Status.FAILURE);
            apiResponse.setMessage(e.getMessage());
            log.info(e.getMessage()+" "+userId);
            return apiResponse;
        }

        accountDTO.setUserID(userId);
        Account account = this.accountConverter.DtoToAccount(accountDTO);
        account.setUser(userFromDb);
        this.accountRepository.save(account);
        apiResponse.setMessage("Account Created Successfully");
        log.info("Account Created for User with UserID {}", userId);

        return apiResponse;

    }

    @Override
    /*
     Function To Fetch Account
     Details for the user
     */
    public ApiResponse<AccountDTO> getAccountDetails(Long userId) {

        log.info("Fetching Account of User with UserID {}", userId);
        ApiResponse<AccountDTO> apiResponse = new ApiResponse<>();
        User userFromDb;
        Account accountFromDb;

        // Check for Valid User
        try {
            userFromDb = this.userRepository.findById(userId).get();
        } catch (Exception e) {
            apiResponse.setStatus(Status.FAILURE);
            apiResponse.setMessage("No user exist with userId " + userId);
            log.info(e.getMessage() + "\nProcessing Failed while Fetching User");
            return apiResponse;
        }

        // Check if User have an account
        try {
            accountFromDb = this.accountRepository.findById(userFromDb.getAccount().getAccountId()).get();
        } catch (NullPointerException e) {
            log.info(e.getMessage());
            apiResponse.setMessage("No Account exist for user with userId " + userId);
            log.info("Processing Failed while Fetching Account");
            return apiResponse;
        }
        AccountDTO accountDTO = accountConverter.accountToDTO(accountFromDb);
        apiResponse.setData(accountDTO);
        apiResponse.setMessage("Account Information Fetched Successfully");
        log.info("Account Fetched Successfully.");
        return apiResponse;
    }
}
