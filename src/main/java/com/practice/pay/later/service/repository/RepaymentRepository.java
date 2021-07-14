package com.practice.pay.later.service.repository;

import com.practice.pay.later.service.model.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepaymentRepository extends JpaRepository<Repayment,String> {


}
