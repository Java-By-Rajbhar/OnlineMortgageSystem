package com.mortgage.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mortgage.api.dto.AccountSummaryResponseDto;
import com.mortgage.api.dto.TransactionSummaryDto;
import com.mortgage.api.service.AccountService;

/**
 * 
 * @author Shashank
 *
 */
@RequestMapping("/api")
@RestController
@CrossOrigin(allowedHeaders = { "*", "/" }, origins = { "*", "/" })
public class AccountSummaryController {
	
	@Autowired
    private AccountService accountService;	
	
	@GetMapping("summary/{userId}")
	public ResponseEntity<AccountSummaryResponseDto> accountSummary(@PathVariable String userId)
	{
		return new ResponseEntity<>(accountService.accountSummary(userId),HttpStatus.OK);
	}
	
	@GetMapping("/transactionSummary/{accountNum}")
	public ResponseEntity<List<TransactionSummaryDto>> transactionSummary(@PathVariable String accountNum)
	{
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
