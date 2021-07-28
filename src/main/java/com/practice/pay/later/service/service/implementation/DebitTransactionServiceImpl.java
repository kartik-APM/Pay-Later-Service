package com.practice.pay.later.service.service.implementation;

import com.practice.pay.later.service.converter.DebitTransactionConverter;
import com.practice.pay.later.service.dto.DebitTransactionDTO;
import com.practice.pay.later.service.enums.Status;
import com.practice.pay.later.service.exception.ApiResponse;
import com.practice.pay.later.service.model.Account;
import com.practice.pay.later.service.model.DebitTransaction;
import com.practice.pay.later.service.model.User;
import com.practice.pay.later.service.repository.AccountRepository;
import com.practice.pay.later.service.repository.DebitTransactionRepository;
import com.practice.pay.later.service.repository.UserRepository;
import com.practice.pay.later.service.service.DebitTransactionService;
import com.practice.pay.later.service.validation.DebitTransactionValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@Slf4j
public class DebitTransactionServiceImpl implements DebitTransactionService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private DebitTransactionRepository debitTransactionRepository;
    @Autowired
    private DebitTransactionConverter debitTransactionConverter;
    @Autowired
    private DebitTransactionValidation debitTransactionValidation;


    @Transactional
    public void saveTransactionDetails(DebitTransaction debitTransaction) {
        this.debitTransactionRepository.save(debitTransaction);
    }

    /*
    * Service Implementation for Processing
    * the Debit Transaction Details
    * */
    @Override
    public ApiResponse<String> processDebitTransaction(
            DebitTransactionDTO debitTransactionDTO,
            Long userId,
            Long accountId) {

        log.info("Processing Debit Transaction of User with AccountID {}", accountId);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Account accountFromDb = null;
        User userFromDb = null;

        /*
         * Null Check for User
         * */
        try{
            userFromDb = this.userRepository.findById(userId).get();
        }catch (Exception e){
            log.info(e.getMessage());
            apiResponse.setStatus(Status.FAILURE);
            apiResponse.setMessage("No user exist with userId " + userId);
            log.info("Processing Failed while Fetching User");
            return apiResponse;
        }

        /*
         * Null Check for Account
         * */
        try {
            accountFromDb = this.accountRepository.findById(accountId).get();
        } catch (Exception e) {
            log.info(e.getMessage());
            apiResponse.setStatus(Status.FAILURE);
            apiResponse.setMessage("Wrong accountId passed for the user with userId " + userId);
            log.info("Processing Failed while Fetching Account");
            return apiResponse;
        }

        /*
         * Error check for User/Account
         * */
        if (userFromDb.getAccount() != accountFromDb) {
            try {
                throw new NoSuchElementException("User and Account details mismatched");
            } catch (NoSuchElementException e) {
                log.info(e.getMessage());
                apiResponse.setStatus(Status.FAILURE);
                apiResponse.setMessage(e.getMessage());
                log.info("Processing Failed");
            }
        } else {
            DebitTransaction debitTransaction =
                    this.debitTransactionConverter.DtoToDebitTransaction(debitTransactionDTO);
            debitTransaction.setAccount(accountFromDb);
            int currentAvailableLimit =
                    accountFromDb.getAvailableCreditLimit() - debitTransactionDTO.getAmount();
            Boolean status =
                    this.debitTransactionValidation.check(currentAvailableLimit);
            if (status) {
                accountFromDb.setAvailableCreditLimit(currentAvailableLimit);
                debitTransaction.setStatus(Status.SUCCESSFUL);
                apiResponse.setMessage("Transaction Processed Successfully");
            } else {
                debitTransaction.setStatus(Status.FAILURE);
                apiResponse.setMessage("Available limit is lower than the Amount need to be charge.");
            }
            saveTransactionDetails(debitTransaction);
            log.info("Transaction Processed Successfully for AccountID " + accountId);
        }
        return apiResponse;
    }


    @Override
    public List<DebitTransactionDTO> getAllDebitTransaction(Long accountId) {

        List<ArrayList> arrayLists =  debitTransactionRepository.getAllDebitTransaction(accountId);

        return debitTransactionConverter.arrayListToDTOList(arrayLists);
    }

}
