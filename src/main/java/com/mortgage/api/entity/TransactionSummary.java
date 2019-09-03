package com.mortgage.api.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class TransactionSummary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionId;
	private String accountNo;
	private String transactionType;
	private LocalDateTime transactionDateTime;
	private Double amount;
	private String description;
}
