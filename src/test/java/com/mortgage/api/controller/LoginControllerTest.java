package com.mortgage.api.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mortgage.api.dto.LoginRequestDto;
import com.mortgage.api.dto.LoginResponseDto;
import com.mortgage.api.service.LoginServiceimpl;


@RunWith(SpringJUnit4ClassRunner.class)
public class LoginControllerTest {

	
	
	@InjectMocks
	LoginController loginControllerMock;

	private MockMvc mockMvc;
	
	@Mock
	LoginServiceimpl loginServiceimpl;
	
	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(loginControllerMock).build();
	}

	
	
	
	
	@Test
	public void testLogin() {

		LoginResponseDto loginResponseDto=new LoginResponseDto();
		loginResponseDto.setMessage("Login Sucess");
		loginResponseDto.setStatus("Sucess");
		loginResponseDto.setStatusCode(201);
		
		when(loginServiceimpl.login(Mockito.any())).thenReturn(loginResponseDto);
		
		LoginRequestDto loginRequestDto=new LoginRequestDto();
		loginRequestDto.setPassword("ok");
		loginRequestDto.setUserId("sk");
		ResponseEntity<LoginResponseDto> actual=loginControllerMock.login(loginRequestDto);
		
		
		assertEquals(201, actual.getBody().getStatusCode());
	
	}

}
