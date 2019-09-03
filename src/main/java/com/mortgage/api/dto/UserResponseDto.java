package com.mortgage.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Sushil
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class UserResponseDto {

	private String userId;
	private String mortgageAccount;
	private String transactionAccount;
	private int statusCode;
	private String message;
}
