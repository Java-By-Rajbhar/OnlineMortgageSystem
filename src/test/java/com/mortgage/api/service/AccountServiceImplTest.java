package com.mortgage.api.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mortgage.api.dto.AccountSummaryResponseDto;
import com.mortgage.api.dto.TransactionSummaryDto;
import com.mortgage.api.entity.Account;
import com.mortgage.api.entity.User;
import com.mortgage.api.repository.AccountRepository;
import com.mortgage.api.repository.TransactionSummary;
import com.mortgage.api.repository.TransactionSummaryRepository;
import com.mortgage.api.repository.UserRepository;


@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceImplTest {
	
	
	
	@Mock
	private AccountRepository accountRespository;
	
	@Mock
	private UserRepository userRepository;
	
	
	@Mock
	private TransactionSummaryRepository transactionSummaryRepository;
	
	@InjectMocks
	private AccountServiceImpl accountServiceimpl;
	
	

	@Test
	public void transactionListTest() {
		
		TransactionSummary transactionSummary=new TransactionSummary();
		transactionSummary.setAccountNo("1234");
		transactionSummary.setAmount(50000.0);
		transactionSummary.setDescription("desc1");
		transactionSummary.setTransactionDateTime(LocalDateTime.now());
		transactionSummary.setTransactionId(1);
		transactionSummary.setTransactionType("CREDIT");
		List<TransactionSummary> listSummary=new ArrayList<>();
		listSummary.add(transactionSummary);

	when(transactionSummaryRepository.findByAccountNo(Mockito.anyString())).thenReturn(listSummary);
	List<TransactionSummaryDto> actual=accountServiceimpl.transactionList("1234");
	assertEquals(1, actual.size());
	}

	@Test
	public void accountSummaryTest() {
	
	     User user=new User();
	     user.setAddress("BTM");
	     user.setCity("BANGALORE");
	     user.setDateOfBirth(LocalDate.now());
	     user.setDepositAmount(50000.0);
	     user.setEmailId("shashank1234@gmail.com");
	     user.setFirstName("shashank");
	     user.setGender("male");
	     user.setId(1);
	     user.setIdNumber("CKL5");
	     user.setLastName("Kumar");
	     user.setMobileNo(8970297757L);
	     user.setOccupation("salary");
	     user.setPinCode(560067);
	     user.setProductName("home loan");
	     user.setPropertyValue(5000000.0);
	     user.setSalutation("Mr.");
	     user.setUserId("sk");
	     
	     Account account1 =new Account();
	     account1.setAccountNo("1234");
	     account1.setAccountType("transaction");
	     account1.setAmount(50000.0);
	     account1.setId(1);
	     account1.setUserId("sk");
	     
	     Account account2 =new Account();
	     account2.setAccountNo("1234");
	     account2.setAccountType("transaction");
	     account2.setAmount(50000.0);
	     account2.setId(1);
	     account2.setUserId("sk");
	     
	     List<Account> listAcount=new ArrayList<>();
	     listAcount.add(account1);
	     listAcount.add(account2);
		
		when(userRepository.findByUserId(Mockito.anyString())).thenReturn(user);
		
		when(accountRespository.findByUserId(Mockito.anyString())).thenReturn(listAcount);
		AccountSummaryResponseDto AccountSummaryResponseDto=accountServiceimpl.accountSummary("sk");

		assertEquals("Mr.", AccountSummaryResponseDto.getSalutation());
		
	}

}
