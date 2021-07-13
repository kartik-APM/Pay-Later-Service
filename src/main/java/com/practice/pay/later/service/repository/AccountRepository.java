package com.practice.pay.later.service.repository;

import com.practice.pay.later.service.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {

}
