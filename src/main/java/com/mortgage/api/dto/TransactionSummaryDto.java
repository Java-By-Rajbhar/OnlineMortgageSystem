package com.mortgage.api.dto;


import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionSummaryDto {

	private int transactionId;
	private String accountNo;
	private String transactionType;
	private LocalDateTime transactionDateTime;
	private double amount;
	private String desc;
	
}