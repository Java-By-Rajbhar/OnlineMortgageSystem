package com.mortgage.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mortgage.api.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

}
