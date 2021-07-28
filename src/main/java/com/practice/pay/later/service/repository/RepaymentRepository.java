package com.practice.pay.later.service.repository;

import com.practice.pay.later.service.model.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface RepaymentRepository extends JpaRepository<Repayment,String> {

    @Query("SELECT " +
            "debitTransactionId, amount, orderId, transactionDate, status, account.accountId " +
            "FROM DebitTransaction WHERE account.accountId = ?1")
    List<ArrayList> getAllRepayments(Long accountId);

}