package com.mortgage.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mortgage.api.dto.AccountSummaryResponseDto;
import com.mortgage.api.dto.TransactionSummaryDto;
import com.mortgage.api.entity.Account;
import com.mortgage.api.entity.TransactionSummary;
import com.mortgage.api.entity.User;
import com.mortgage.api.repository.AccountRepository;
import com.mortgage.api.repository.TransactionSummaryRepository;
import com.mortgage.api.repository.UserRepository;


@Service
public class AccountServiceImpl implements AccountService {

	
	
	@Autowired
	private AccountRepository accountRespository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private TransactionSummaryRepository transactionSummaryRepository;
	
	@Override
	public AccountSummaryResponseDto accountSummary(String userId) {
		
		AccountSummaryResponseDto accountSummaryResponseDto=new AccountSummaryResponseDto();

		User userrepo=userRepository.findByUserId(userId);
		
		accountSummaryResponseDto.setFirstName(userrepo.getFirstName());
		accountSummaryResponseDto.setLastName(userrepo.getLastName());
		accountSummaryResponseDto.setProductName(userrepo.getProductName());
		accountSummaryResponseDto.setPropertyValue(userrepo.getPropertyValue());
		accountSummaryResponseDto.setSalutation(userrepo.getSalutation());
		
		List<Account> listaccount=accountRespository.findByUserId(userId);
		for (Account account : listaccount) {
			if(account.getAccountType().equalsIgnoreCase("mortgage"))
			{
				accountSummaryResponseDto.setMortgageAccount(account.getAccountNo());
				accountSummaryResponseDto.setMortgageBalance(account.getAmount());
				
			}
			if(account.getAccountType().equalsIgnoreCase("transaction"))
			{
				accountSummaryResponseDto.setTransactionAccount(account.getAccountNo());
				accountSummaryResponseDto.setTransactionAmount(account.getAmount());
			}
		}
		
		return accountSummaryResponseDto;
	}


	@Override
	public List<TransactionSummaryDto> transactionList(String accountNumber) {

		List<TransactionSummaryDto> listTransactionSummary=new ArrayList<>();
		List<TransactionSummary> listTransaction=transactionSummaryRepository.findByAccountNo(accountNumber);
		for (TransactionSummary transactionSummary : listTransaction) {
			TransactionSummaryDto transactionSummaryDto=new TransactionSummaryDto();
			transactionSummaryDto.setAccountNo(transactionSummary.getAccountNo());
			transactionSummaryDto.setAmount(transactionSummary.getAmount());
			transactionSummaryDto.setTransactionDateTime(transactionSummary.getTransactionDateTime());
			transactionSummaryDto.setTransactionType(transactionSummary.getTransactionType());
			listTransactionSummary.add(transactionSummaryDto);
		}
		
		
		
		return listTransactionSummary;
	}

}
