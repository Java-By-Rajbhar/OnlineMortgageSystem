package com.mortgage.api.dto;


import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionSummaryDto {

	private int TransactionId;
	private String accountNo;
	private String TransactionType;
	private LocalDateTime TransactionDateTime;
	private double amount;
	
}