package com.practice.pay.later.service.repository;

import com.practice.pay.later.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstNameIgnoreCase(String firstName);

    List<User> findByLastNameIgnoreCase(String lstName);

    User findByEmailId(String emailId);

    @Query("SELECT s FROM User s WHERE s.emailId = ?1")
    Optional<User> findUserByEmail(String emailId);

}
