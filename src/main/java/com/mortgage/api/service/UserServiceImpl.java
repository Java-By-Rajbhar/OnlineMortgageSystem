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
import com.mortgage.api.exception.UserIdAlreadyExitsException;
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
	
	private static final String MORTGAGE = "Mortgage";
	private static final String TRANSACTION = "Transaction";

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	LoginRepository loginRepository;
	@Autowired
	UserUtility userUtility;
	@Autowired
	MailService mailService;
	@Autowired
	SmsService smsService;

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
		// login object
		Login login = new Login();
		// Account object
		Account account = new Account();
		// User Object
		User user = new User();
		// get generated userId ,password and age calculation

		String userIdG = userUtility.generateUserId(userRequestDto.getFirstName());
		LOGGER.info("generated userId ={}", userIdG);
		Login login1 = loginRepository.findByUserId(userIdG);
		if (login1 == null) {
			login.setUserId(userIdG);
			String passwordG = userUtility.generatePassword(userRequestDto.getFirstName());
			LOGGER.info("generated password ={}", passwordG);
			login.setPassword(passwordG);
			// save login data
			loginRepository.save(login);
			int age = userUtility.calculateAge(userRequestDto.getDateOfBirth());
			if (age >= 18) {

				// set account data for mortgage
				account.setAccountNo(userUtility.generateAccountNumber(MORTGAGE));
				account.setAccountType(MORTGAGE);
				account.setAmount(userRequestDto.getPropertyValue());
				account.setUserId(userIdG);
				// save account data
				accountRepository.save(account);
				// set account data for transaction
				Account account1 = new Account();
				account1.setAccountNo(userUtility.generateAccountNumber(TRANSACTION));
				account1.setAccountType(TRANSACTION);
				account1.setAmount(userRequestDto.getDepositAmount());
				account1.setUserId(userIdG);
				// save account data for transaction
				accountRepository.save(account1);
				BeanUtils.copyProperties(userRequestDto, user);
				// save user data
				user.setUserId(userIdG);
				userRepository.save(user);
				smsService.sms("Login for INGMortgage\n UserId :" +userIdG+"\n Password :"+passwordG);
				mailService.sendEmail(user.getEmailId(), userIdG, passwordG);
				
				// get account object
				Account mortAccount = accountRepository.findByAccountTypeAndUserId(MORTGAGE, userIdG);
				Account transAccount = accountRepository.findByAccountTypeAndUserId(TRANSACTION, userIdG);
				// generate response
				UserResponseDto responseDto = new UserResponseDto();
				responseDto.setMessage("user has added successfully");
				responseDto.setStatusCode(HttpStatus.CREATED.value());
				responseDto.setMortgageAccount(mortAccount.getAccountNo());
				responseDto.setTransactionAccount(transAccount.getAccountNo());
				responseDto.setUserId(userIdG);

				return responseDto;
			} else {
				throw new AgeNotValidException("You are under age");
			}
		} else {
			throw new UserIdAlreadyExitsException("UserId is already exits");
		}

	}

}