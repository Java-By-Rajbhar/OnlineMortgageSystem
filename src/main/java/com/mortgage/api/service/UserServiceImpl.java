package com.mortgage.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mortgage.api.dto.UserRequestDto;
import com.mortgage.api.dto.UserResponseDto;
import com.mortgage.api.entity.Account;
import com.mortgage.api.entity.Login;
import com.mortgage.api.entity.User;
import com.mortgage.api.exception.AgeNotValidException;
import com.mortgage.api.repository.AccountRepository;
import com.mortgage.api.repository.LoginRepository;
import com.mortgage.api.repository.UserRepository;
import com.mortgage.api.utility.UserUtility;

/**
 * 
 * @author Sushil
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	LoginRepository loginRepository;
	@Autowired
	UserUtility userUtility;

	/**
	 * This method is use to add mortgage user
	 * 
	 * @param userRequestDto not null
	 * @return UserResponseDto not null
	 * @exception AgeNotValidException throws if age is less than 18
	 */
	@Override
	public UserResponseDto addMortgageDetail(UserRequestDto userRequestDto) {
		LOGGER.info("Inside addMortgageDetail of UserServiceImpl class");
		//login object
		Login login = new Login();
		//Account object
		Account account = new Account();
		//User Object
		User user = new User();
		//get generated userId ,password and age calculation
		
		String userId_g= userUtility.generateUserId(userRequestDto.getFirstName());
		LOGGER.info("generated userId ={}",userId_g);
		login.setUserId(userId_g);
		String password_g=userUtility.generatePassword(userRequestDto.getFirstName());
		LOGGER.info("generated password ={}",password_g);
		login.setPassword(password_g);
		int age  = userUtility.calculateAge(userRequestDto.getDateOfBirth());
		if(age>=18)
		{
			//save login data
			loginRepository.save(login);
			//set account data for mortgage
			account.setAccountNo("M10101");
			account.setAccountType("Mortgage");
			account.setAmount(userRequestDto.getPropertyValue());
			account.setUserId(userId_g);
			//save account data
			accountRepository.save(account);
			//set account data for transaction
			Account account1 = new Account();
			account1.setAccountNo("T10101");
			account1.setAccountType("Transaction");
			account1.setAmount(userRequestDto.getDepositAmount());
			account1.setUserId(userId_g);
			//save account data for transaction
			accountRepository.save(account1);
			BeanUtils.copyProperties(userRequestDto, user);
			//save user data
			userRepository.save(user);
			//get account object
			Account mortAccount  = accountRepository.findByAccountTypeAndUserId("Mortgage",userId_g);
			Account transAccount  = accountRepository.findByAccountTypeAndUserId("Transaction",userId_g);
			//generate response
			UserResponseDto responseDto = new UserResponseDto();
			responseDto.setMessage("user has added successfully");
			responseDto.setStatusCode(HttpStatus.CREATED.value());
			responseDto.setMortgageAccount(mortAccount.getAccountNo());
			responseDto.setTransactionAccount(transAccount.getAccountNo());
			responseDto.setUserId(userId_g);
			
			return responseDto;
		}
		else
		{
			throw new AgeNotValidException("You are under age");
		}

	}

}
