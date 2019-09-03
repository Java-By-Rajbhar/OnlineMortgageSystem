package com.mortgage.api.service;

import com.mortgage.api.dto.LoginRequestDto;
import com.mortgage.api.dto.LoginResponseDto;

/**
 * 
 * @author Sushil
 *
 */
public interface LoginService {

	LoginResponseDto login(LoginRequestDto loginRequestDto);

	
}
