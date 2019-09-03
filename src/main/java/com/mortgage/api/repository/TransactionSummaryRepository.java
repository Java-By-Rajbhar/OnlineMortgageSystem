package com.mortgage.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mortgage.api.entity.TransactionSummary;

@Repository
public interface TransactionSummaryRepository extends JpaRepository<TransactionSummary, Integer> {

}
