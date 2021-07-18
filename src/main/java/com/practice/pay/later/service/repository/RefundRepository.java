package com.practice.pay.later.service.repository;


import com.practice.pay.later.service.model.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {

    @Query("SELECT " +
            "refundTransactionId, amount, originalTransactionId, transactionDate, status " +
            "FROM Refund")
    public List<ArrayList> getAllRefunds();

}
