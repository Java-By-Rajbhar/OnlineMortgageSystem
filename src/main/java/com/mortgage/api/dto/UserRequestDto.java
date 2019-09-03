package com.mortgage.api.dto;

import java.time.LocalDate;

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
public class UserRequestDto {

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
