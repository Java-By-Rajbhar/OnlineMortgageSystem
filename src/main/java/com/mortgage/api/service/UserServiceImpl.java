package com.mortgage.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.mortgage.api.dto.UserRequestDto;
import com.mortgage.api.dto.UserResponseDto;
import com.mortgage.api.entity.Account;
import com.mortgage.api.entity.Login;
import com.mortgage.api.entity.User;
import com.mortgage.api.repository.AccountRepository;
import com.mortgage.api.repository.LoginRepository;
import com.mortgage.api.repository.UserRepository;
import com.mortgage.api.utility.UserUtility;

/**
 * 
 * @author Sushil
 *
 */
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
		account.setAccountNo("T10101");
		account.setAccountType("Transaction");
		account.setAmount(userRequestDto.getDepositAmount());
		account.setUserId(userId_g);
		//save account data for transaction
		accountRepository.save(account);
		BeanUtils.copyProperties(user, userRequestDto);
		//save user data
		userRepository.save(user);
		//generate response
		UserResponseDto responseDto = new UserResponseDto();
		responseDto.setMessage("user has added successfully");
		responseDto.setStatusCode(HttpStatus.CREATED.value());
		return null;
	}

}
