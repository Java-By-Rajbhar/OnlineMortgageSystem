package com.mortgage.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountSummaryResponseDto {
	
	private String salutation;
	private String firstName;
	private String lastName;
	private String productName;
	private double propertyValue;
	private String mortgageAccount;
	private double mortgageBalance;
	private String transactionAccount;
	private double transactionAmount;
	
	

}


