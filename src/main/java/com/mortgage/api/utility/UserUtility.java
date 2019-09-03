package com.mortgage.api.utility;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Sushil
 *
 */
@Component
public class UserUtility {
	
	public String generateUserId(String firstName)
	{
		  String userId=null;
		  LocalDate localDate = LocalDate.of(2019, 10, 23);
	        if(firstName.length()<4) {
	        	userId = firstName+""+localDate.getDayOfMonth()+""+localDate.getMonthValue();
	        }else {
	            userId = firstName.substring(0,4).toUpperCase()+""+localDate.getDayOfMonth()+""+localDate.getMonthValue();
	        }
	        
	        return userId;
	}
	public String generatePassword(String firstName)
	{
		String new_password = firstName+"@123";
		
		return Base64.getEncoder().encodeToString(new_password.getBytes());
		
	}
	
	public int calculateAge(LocalDate dob)
	{
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String formattedString = dob.format(formatter);
		  LocalDate l = LocalDate.parse(formattedString, formatter);//specify year, month, date directly
		  LocalDate now = LocalDate.now(); //gets localDate
		  Period diff = Period.between(l, now); 
		  return diff.getYears();
	}
	
	private UserUtility() {}

}
