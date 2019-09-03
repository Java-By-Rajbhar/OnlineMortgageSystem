package com.mortgage.api.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mortgage.api.dto.AccountSummaryResponseDto;
import com.mortgage.api.dto.TransactionSummaryDto;
import com.mortgage.api.service.AccountServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
public class AccountSummaryControllerTest {
	
	
	
	@InjectMocks
	AccountSummaryController accountSummaryController;

	private MockMvc mockMvc;
	
	@Mock
	AccountServiceImpl accountServiceimpl;
	
	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(accountSummaryController).build();
	}

	@Test
	public void testAccountSummary() throws JsonProcessingException, Exception {
		
		
		AccountSummaryResponseDto accountSummaryResponseDto=new AccountSummaryResponseDto();
		accountSummaryResponseDto.setFirstName("shashank");
		accountSummaryResponseDto.setLastName("kumar");
		accountSummaryResponseDto.setMortgageAccount("5678");
		accountSummaryResponseDto.setMortgageBalance(50000.0);
		accountSummaryResponseDto.setProductName("home loan");
		accountSummaryResponseDto.setPropertyValue(5000000.0);
		accountSummaryResponseDto.setSalutation("Mr.");
		accountSummaryResponseDto.setTransactionAccount("1234");
		accountSummaryResponseDto.setTransactionAmount(450000.0);
		
		
		when(accountServiceimpl.accountSummary(Mockito.anyString())).thenReturn(accountSummaryResponseDto);
		mockMvc.perform(MockMvcRequestBuilders.get("summary/12").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(new ObjectMapper().writeValueAsString(""))).andReturn();
		
		ResponseEntity<AccountSummaryResponseDto> actual=accountSummaryController.accountSummary("sk");
		assertEquals("Mr.", actual.getBody().getSalutation());
		
	}

	@Test
	public void testTransactionSummary() throws JsonProcessingException, Exception {
		
		List<TransactionSummaryDto> listTran=new ArrayList<>();
		TransactionSummaryDto transactionSummaryDto=new TransactionSummaryDto();
		transactionSummaryDto.setAccountNo("1234");
		transactionSummaryDto.setAmount(50000.0);
		transactionSummaryDto.setTransactionDateTime(LocalDateTime.now());
		transactionSummaryDto.setTransactionId(1);
		transactionSummaryDto.setTransactionType("CREDIT");
		
		listTran.add(transactionSummaryDto);
		
		when(accountServiceimpl.transactionList(Mockito.anyString())).thenReturn(listTran);
		mockMvc.perform(MockMvcRequestBuilders.get("summary/12").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(new ObjectMapper().writeValueAsString(""))).andReturn();
		ResponseEntity<List<TransactionSummaryDto>> actual=accountSummaryController.transactionSummary("1234");
		assertEquals(1, actual.getBody().size());
		
	}

}
