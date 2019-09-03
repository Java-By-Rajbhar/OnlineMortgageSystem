package com.mortgage.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionSummaryRepository extends JpaRepository<TransactionSummary, Integer> {
	
	public List<TransactionSummary> findByAccountNo(String accountNumber);

}
