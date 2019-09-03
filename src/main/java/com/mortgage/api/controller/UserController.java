package com.mortgage.api.controller;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mortgage.api.dto.UserRequestDto;
import com.mortgage.api.dto.UserResponseDto;
import com.mortgage.api.service.UserService;

/**
 * 
 * @author Sushil
 *
 */
@RequestMapping("/api")
@RestController
@CrossOrigin(allowedHeaders = { "*", "/" }, origins = { "*", "/" })
public class UserController {
	@Autowired
	UserService userService;
	@PostMapping("/registration")
	public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto)
	{
		UserResponseDto response = userService.addMortgageDetail(userRequestDto);
		
		return new ResponseEntity<UserResponseDto>(response, HttpStatus.CREATED);
		
	}

}
