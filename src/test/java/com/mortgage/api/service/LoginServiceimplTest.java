package com.mortgage.api.service;


import static org.junit.Assert.*;

import org.junit.Test;

public class LoginServiceimplTest {

	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

=======
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.mortgage.api.dto.LoginRequestDto;
import com.mortgage.api.dto.LoginResponseDto;
import com.mortgage.api.entity.Account;
import com.mortgage.api.entity.Login;
import com.mortgage.api.repository.AccountRepository;
import com.mortgage.api.repository.LoginRepository;

@RunWith(SpringRunner.class)
public class LoginServiceimplTest {

	@InjectMocks
	LoginServiceimpl loginServiceImpl;

	@Mock
	LoginRepository loginRepository;
	AccountRepository accountRepository;

	LoginRequestDto loginRequestDto;
	Login login;
	Account account;
	
	LoginResponseDto loginResponseDto;

	@Before
	public void init() {

		login = new Login();
		login.setPassword("Ajith");
		login.setUserId("ajit7890");
		login.setId(1);
		
		account = new Account();
		account.setAccountNo("M10101");
		

		loginRequestDto = new LoginRequestDto();
		loginRequestDto.setPassword("Ajith");
		loginRequestDto.setUserId("ajit7890");

		loginResponseDto = new LoginResponseDto();
		loginResponseDto.setMessage("LOGGED IN SUCCESSFULLY");
		loginResponseDto.setMortgageAccountNo("M10101");
		loginResponseDto.setTransactionAccountNo("T10101");
		loginResponseDto.setUserId("ajit7890");
		loginResponseDto.setStatus("Success");
		loginResponseDto.setMessage("Logged in successfully");

	}

	@Test
	public void login() {

		Mockito.when(loginRepository.findByUserIdAndPassword(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(login);
		LoginResponseDto loginResponseDto = loginServiceImpl.login(loginRequestDto);
		Assert.assertEquals(loginResponseDto.getUserId(), login.getUserId());

	}

}
