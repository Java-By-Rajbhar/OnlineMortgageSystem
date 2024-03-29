package com.mortgage.api.utility;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mortgage.api.repository.AccountRepository;

/**
 * 
 * @author Sushil
 *
 */
@Component
public class UserUtility {

	private static final String MORTGAGE = "Mortgage";
	private static final String TRANSACTION = "Transaction";
	private static final String MING ="MING";
	private static final String TING ="TING";
	@Autowired
	AccountRepository accountRepository;

	public String generateUserId(String firstName) {
		String userId = null;
		LocalDate localDate = LocalDate.of(2019, 10, 23);
		if (firstName.length() < 4) {
			userId = firstName + "" + localDate.getDayOfMonth() + "" + localDate.getMonthValue();
		} else {
			userId = firstName.substring(0, 4).toUpperCase() + "" + localDate.getDayOfMonth() + ""
					+ localDate.getMonthValue();
		}

		return userId;
	}

	public String generatePassword(String firstName) {
		String newPassword = firstName + "@123";

		return Base64.getEncoder().encodeToString(newPassword.getBytes());

	}

	public int calculateAge(LocalDate dob) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String formattedString = dob.format(formatter);
		LocalDate l = LocalDate.parse(formattedString, formatter);// specify year, month, date directly
		LocalDate now = LocalDate.now(); // gets localDate
		Period diff = Period.between(l, now);
		return diff.getYears();
	}

	public String generateAccountNumber(String accountType) {
		String mortgageAccountNum = null;
		String accNum = accountRepository.getMaxAccountNumber(accountType);
		if (accountType.equalsIgnoreCase(MORTGAGE) && accNum != null) {
			int account = Integer.parseInt(accNum.substring(4));
			mortgageAccountNum = MING + String.valueOf(account + 1);
		} else if (accountType.equalsIgnoreCase(TRANSACTION) && accNum != null) {
			int account = Integer.parseInt(accNum.substring(4));
			mortgageAccountNum = TING + String.valueOf(account + 1);
		} else if (accNum == null && accountType.equalsIgnoreCase(MORTGAGE)) {
			String padedNum = String.format("%06d", 1);
			mortgageAccountNum = "MING1" + padedNum;
		} else if (accNum == null && accountType.equalsIgnoreCase(TRANSACTION)) {
			String padedNum = String.format("%06d", 1);
			mortgageAccountNum = "TING2" + padedNum;
		}

		return mortgageAccountNum;
	}

	private UserUtility() {
	}

}
