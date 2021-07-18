package com.practice.pay.later.service.repository;

import com.practice.pay.later.service.model.DebitTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface DebitTransactionRepository extends JpaRepository<DebitTransaction,Long> {

    @Query("SELECT " +
            "debitTransactionId, amount, orderId, transactionDate, status " +
            "FROM DebitTransaction")
    public List<ArrayList> getAllDebitTransaction();

}
