package com.mortgage.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Sushil
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDto {
	private String userId;
	private String transactionAccountNo;
	private String mortgageAccountNo;
	private String message;
	private String status;
	private int statusCode;


}
