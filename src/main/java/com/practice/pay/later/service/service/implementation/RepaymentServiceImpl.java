package com.practice.pay.later.service.service.implementation;

import com.practice.pay.later.service.dto.converter.RepaymentConverter;
import com.practice.pay.later.service.dto.RepaymentDTO;
import com.practice.pay.later.service.enums.Status;
import com.practice.pay.later.service.exception.ApiResponse;
import com.practice.pay.later.service.model.Account;
import com.practice.pay.later.service.model.Repayment;
import com.practice.pay.later.service.model.User;
import com.practice.pay.later.service.repository.AccountRepository;
import com.practice.pay.later.service.repository.RepaymentRepository;
import com.practice.pay.later.service.repository.UserRepository;
import com.practice.pay.later.service.service.RepaymentService;
import com.practice.pay.later.service.validation.RepaymentTransactionValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RepaymentServiceImpl implements RepaymentService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final RepaymentConverter repaymentConverter;
    private final RepaymentRepository repaymentRepository;
    private final RepaymentTransactionValidation repaymentTransactionValidation;
    @Autowired
    public RepaymentServiceImpl(
            final UserRepository userRepository,
            final AccountRepository accountRepository,
            final RepaymentConverter repaymentConverter,
            final RepaymentRepository repaymentRepository,
            final RepaymentTransactionValidation repaymentTransactionValidation){
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.repaymentConverter = repaymentConverter;
        this.repaymentRepository = repaymentRepository;
        this.repaymentTransactionValidation = repaymentTransactionValidation;
    }

    @Transactional
    public void saveRepaymentTransactionDetails(Repayment repayment){
        this.repaymentRepository.save(repayment);
    }

    /**
     * Service Implementation for Processing
     * the Repayment Transaction Details
     */
    @Override
    public ApiResponse<String> processRepaymentTransaction(
            RepaymentDTO repaymentDTO,
            Long userId) {

        log.info("Processing Repayment Transaction for User with UserId {}", userId);
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

        Repayment repayment =
                this.repaymentConverter.DtoToRepaymentTransaction(repaymentDTO);
        repayment.setAccount(accountFromDb);
        int totalAmount = repayment.getAmount() + accountFromDb.getAvailableCreditLimit();
        Boolean status =
                this.repaymentTransactionValidation.checkAmount(accountFromDb.getAuthorisedCreditLimit(), totalAmount);
        if (status) {
            repayment.setStatus(Status.SUCCESSFUL);
            accountFromDb.setAvailableCreditLimit(totalAmount);
            apiResponse.setMessage("Repayment Transaction Processed Successfully");
        }else {
            repayment.setStatus(Status.FAILURE);
            apiResponse.setMessage("Repayment Failed as Amount is higher than expected");
            log.info("Repayment Failed as Amount is higher than expected");
            return apiResponse;
        }
        saveRepaymentTransactionDetails(repayment);
        log.info("Repayment Transaction Processed Successfully");
        return apiResponse;
    }

    @Override
    public List<ArrayList> getAllRepayment(Long accountId) {

        return repaymentRepository.getAllRepayments(accountId);
    }

}
