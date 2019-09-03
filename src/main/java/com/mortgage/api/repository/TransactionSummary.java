package com.mortgage.api.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class TransactionSummary {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transactionId ;
	private String accountNo; 
	private String transactionType; 
	private LocalDateTime transactionDateTime; 
	private double amount; 
	private String description; 

}



