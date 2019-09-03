package com.mortgage.api.service;

import java.util.List;

import com.mortgage.api.dto.AccountSummaryResponseDto;
import com.mortgage.api.dto.TransactionSummaryDto;

public interface AccountService {

	public AccountSummaryResponseDto accountSummary(String userId);
	
	public List<TransactionSummaryDto> transactionList(String accountNumber);
}
