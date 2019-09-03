package com.mortgage.api.entity;

import java.time.LocalDate;

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
 * @author Sushil
 *
 */
@Entity
@Table(name="User_detail")
@Setter
@Getter
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userId;
	private String salutation;
	private String firstName;
	private String lastName;
	private String gender;
	private LocalDate dateOfBirth;
	private String emailId;
	private long mobileNo;
	private String occupation;
	private String city;
	private String address;
	private int pinCode;
	private String idNumber;
	private String productName;
	private double propertyValue;
	private double depositAmount;

}
