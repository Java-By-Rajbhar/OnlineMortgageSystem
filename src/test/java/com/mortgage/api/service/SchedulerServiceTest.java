package com.mortgage.api.service;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.mortgage.api.entity.Account;
import com.mortgage.api.repository.AccountRepository;
import com.mortgage.api.repository.TransactionSummaryRepository;
import com.mortgage.api.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class SchedulerServiceTest {

	@Mock
	private AccountRepository accountRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private TransactionSummaryRepository txnRepository;
	
	@InjectMocks
	private SchedulerServiceImpl schedulerServiceImpl;

	List<String> userIds = new ArrayList<>();
	Account mortgageAccount = new Account();
	Account transactionAccount = new Account();

	@Before
	public void setUp() {
		userIds.add("LAXM1212");
		userIds.add("abhi1212");

		mortgageAccount.setAmount(200.0);
		transactionAccount.setAmount(200.0);
	}

	@Test
	public void testRunTask() {

		Mockito.when(userRepository.getDistinctUserId()).thenReturn(userIds);
		Mockito.when(accountRepository.findByAccountTypeAndUserId(Mockito.anyString(), Mockito.anyString() ))
				.thenReturn(mortgageAccount);
		Mockito.lenient().doNothing().when(accountRepository.save(mortgageAccount));
		
		
//		verify(mock)
		
		/*
		 * Mockito.when(accountRepository.findByAccountTypeAndUserId("Transaction",
		 * Mockito.anyString())) .thenReturn(transactionAccount);
		 */
//		Mockito.when(accountRepository.save(Mockito.any())).thenReturn());
//		Mockito.when(accountRepository.save(Mockito.anyObject())).thenReturn(Mockito.anyObject());
//		Mockito.when(txnRepository.save(Mockito.anyObject())).thenReturn(Mockito.anyObject());
//		Mockito.when(txnRepository.save(Mockito.anyObject())).thenReturn(Mockito.anyObject());
	}
}
