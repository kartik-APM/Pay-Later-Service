package com.practice.pay.later.service.repository;

import com.practice.pay.later.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public List<User> findByFirstNameIgnoreCase(String firstName);

    public List<User> findByLastNameIgnoreCase(String lstName);

    public User findByEmailId(String emailId);

}
