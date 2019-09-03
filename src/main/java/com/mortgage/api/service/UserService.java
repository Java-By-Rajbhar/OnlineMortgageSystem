package com.mortgage.api.service;

import com.mortgage.api.dto.UserRequestDto;
import com.mortgage.api.dto.UserResponseDto;

/**
 * 
 * @author Sushil
 *
 */

public interface UserService {
	
	public UserResponseDto addMortgageDetail(UserRequestDto userRequestDto );

}
