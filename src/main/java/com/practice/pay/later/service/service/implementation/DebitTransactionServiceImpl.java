package com.practice.pay.later.service.service.implementation;

import com.practice.pay.later.service.dto.converter.DebitTransactionConverter;
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

@Service
@Slf4j
public class DebitTransactionServiceImpl implements DebitTransactionService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final DebitTransactionConverter debitTransactionConverter;
    private final DebitTransactionValidation debitTransactionValidation;
    private final DebitTransactionRepository debitTransactionRepository;
    @Autowired
    public DebitTransactionServiceImpl(
            final UserRepository userRepository,
            final AccountRepository accountRepository,
            final DebitTransactionConverter debitTransactionConverter,
            final DebitTransactionValidation debitTransactionValidation,
            final DebitTransactionRepository debitTransactionRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.debitTransactionConverter = debitTransactionConverter;
        this.debitTransactionValidation = debitTransactionValidation;
        this.debitTransactionRepository = debitTransactionRepository;
    }


    @Transactional
    public void saveTransactionDetails(final DebitTransaction debitTransaction) {
        this.debitTransactionRepository.save(debitTransaction);
    }

    /**
     * Service Implementation for Processing
     * the Debit Transaction Details
     */
    @Override
    public ApiResponse<String> processDebitTransaction(
            DebitTransactionDTO debitTransactionDTO,
            Long userId) {

        log.info("Processing Debit Transaction for User with UserId {}", userId);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Account accountFromDb;
        User userFromDb;

        //Null Check for User
        try {
            userFromDb = this.userRepository.findById(userId).get();
        } catch (Exception e) {
            log.info(e.getMessage());
            apiResponse.setStatus(Status.FAILURE);
            apiResponse.setMessage("No user exist with userId " + userId);
            log.info("Processing Failed while Fetching User");
            return apiResponse;
        }

        //Null Check for Account
        try {
            accountFromDb = this.accountRepository.findById(userFromDb.getAccount().getAccountId()).get();
        } catch (Exception e) {
            log.info(e.getMessage());
            apiResponse.setStatus(Status.FAILURE);
            apiResponse.setMessage("Wrong accountId passed for the user with userId " + userId);
            log.info("Processing Failed while Fetching Account");
            return apiResponse;
        }

        DebitTransaction debitTransaction =
                this.debitTransactionConverter.DtoToDebitTransaction(debitTransactionDTO);
        debitTransaction.setAccount(accountFromDb);
        int currentAvailableLimit =
                accountFromDb.getAvailableCreditLimit() - debitTransactionDTO.getAmount();
        Boolean status =
                this.debitTransactionValidation.check(currentAvailableLimit);
        if (status) {
            debitTransaction.setStatus(Status.SUCCESSFUL);
            accountFromDb.setAvailableCreditLimit(currentAvailableLimit);
            apiResponse.setMessage("Transaction Processed Successfully");
        } else {
            debitTransaction.setStatus(Status.FAILURE);
            apiResponse.setMessage("Transaction Failed due to insufficient Balance");
            log.info("Transaction Failed due to insufficient Balance");
            return apiResponse;
        }
        saveTransactionDetails(debitTransaction);
        log.info("Transaction Processed Successfully");
        return apiResponse;
    }


    @Override
    public ApiResponse<List<DebitTransactionDTO>> getAllDebitTransaction(Long userId) {

        log.info("Fetching Debit Transaction for User with UserId {}", userId);
        ApiResponse<List<DebitTransactionDTO>> apiResponse = new ApiResponse<>();
        Account accountFromDb;
        User userFromDb;

        //Null Check for User
        try {
            userFromDb = this.userRepository.findById(userId).get();
        } catch (Exception e) {
            log.info(e.getMessage());
            apiResponse.setStatus(Status.FAILURE);
            apiResponse.setMessage("No user exist with userId " + userId);
            log.info("Processing Failed while Fetching User");
            return apiResponse;
        }

        //Null Check for Account
        try {
            accountFromDb = this.accountRepository.findById(userFromDb.getAccount().getAccountId()).get();
        } catch (Exception e) {
            apiResponse.setStatus(Status.FAILURE);
            apiResponse.setMessage("No account exist for the user with userId " + userId);
            log.info("Processing Failed while Fetching Account");
            return apiResponse;
        }


        final List<ArrayList> arrayLists = debitTransactionRepository.getAllDebitTransaction(accountFromDb.getAccountId());
        apiResponse.setData(debitTransactionConverter.arrayListToDTOList(arrayLists));
        apiResponse.setMessage("Debit Transaction data Fetched Successfully");
        log.info("Debit Transaction data Fetched Successfully");
        return apiResponse;
    }

}
