package com.mortgage.api.service;

import com.mortgage.api.dto.AccountSummaryResponseDto;

public interface AccountService {

	public AccountSummaryResponseDto accountSummary(String userId);
	
}
