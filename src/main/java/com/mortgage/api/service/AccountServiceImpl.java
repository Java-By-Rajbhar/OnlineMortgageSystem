package com.mortgage.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mortgage.api.dto.AccountSummaryResponseDto;
import com.mortgage.api.repository.AccountRepository;


@Service
public class AccountServiceImpl implements AccountService {

	
	
	@Autowired
	private AccountRepository accountRespository;
	
	
	@Override
	public AccountSummaryResponseDto accountSummary(String userId) {

		
		return null;
	}

}
