package com.mortgage.api.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

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
import com.mortgage.api.dto.UserRequestDto;
import com.mortgage.api.dto.UserResponseDto;
import com.mortgage.api.repository.UserRepository;
import com.mortgage.api.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
	
	
	
	@InjectMocks
	UserController userController;

	private MockMvc mockMvc;
	
	@Mock
	UserServiceImpl userServiceimpl;
	
	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testAddUser() throws JsonProcessingException, Exception {

		UserRequestDto userRequestDto =new UserRequestDto();
		userRequestDto.setAddress("BTM");
		userRequestDto.setCity("Bangalore");
		userRequestDto.setDateOfBirth(LocalDate.now());
		userRequestDto.setDepositAmount(50000.0);
		userRequestDto.setFirstName("shashank");
		userRequestDto.setGender("male");
		userRequestDto.setIdNumber("ckl5");
		userRequestDto.setLastName("kumar");
		userRequestDto.setMobileNo(89702097757L);
		userRequestDto.setOccupation("SE");
		userRequestDto.setPinCode(560078);
		userRequestDto.setProductName("home");
		userRequestDto.setPropertyValue(4500000.0);
		userRequestDto.setSalutation("Mr.");
		
		UserResponseDto userRes=new UserResponseDto();
		userRes.setMessage("Added sucessFul");
		userRes.setMortgageAccount("5678");
		userRes.setTransactionAccount("1234");
		userRes.setStatusCode(201);
		userRes.setUserId("sk");
		
		when(userServiceimpl.addMortgageDetail(Mockito.any())).thenReturn(userRes);
		mockMvc.perform(MockMvcRequestBuilders.get("registration").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(new ObjectMapper().writeValueAsString(""))).andReturn();
		
		ResponseEntity<UserResponseDto> actual=	userController.addUser(userRequestDto);
	assertEquals(201, actual.getBody().getStatusCode());
		
	}

}
