package com.mortgage.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Shashank	
 *
 */
@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class Account {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String userId;
	private String accountNo;
	private double amount;
	private String accountType;
	
}



