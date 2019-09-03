package com.mortgage.api.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Sushil
 *
 */
@Setter
@Getter
public class LoginRequestDto {

	private String userId;
	private String password;
}
