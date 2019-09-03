package com.mortgage.api.service;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mortgage.api.dto.LoginRequestDto;
import com.mortgage.api.dto.LoginResponseDto;
import com.mortgage.api.entity.Account;
import com.mortgage.api.entity.Login;
import com.mortgage.api.exception.InvalidCredentialsException;
import com.mortgage.api.repository.AccountRepository;
import com.mortgage.api.repository.LoginRepository;

/**
 * 
 * @author Sushil
 *
 */
@Service
public class LoginServiceimpl implements LoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceimpl.class);

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	SmsService smsService;

	@Autowired
	MailService mailService;

	@Override
	public LoginResponseDto login(LoginRequestDto loginRequestDto) {
		LOGGER.info("inside Login");
		LoginResponseDto loginResponseDto = new LoginResponseDto();

		String encodedPassword = loginRequestDto.getPassword();
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] decodedString = decoder.decode(encodedPassword);
		String decodedPassword = new String(decodedString);
		LOGGER.info("decodedPassword ={}", decodedPassword);

		Login login = loginRepository.findByUserIdAndPassword(loginRequestDto.getUserId(), encodedPassword);

		if (login != null) {
			

			loginResponseDto.setUserId(login.getUserId());

			String transaction = "Transaction";
			String mortgage = "Mortgage";

			Account transactionAccount = accountRepository.findByUserIdAndAccountType(login.getUserId(), transaction);
			Account mortgageAccount = accountRepository.findByUserIdAndAccountType(login.getUserId(), mortgage);

			loginResponseDto.setTransactionAccountNo(transactionAccount.getAccountNo());
			loginResponseDto.setMortgageAccountNo(mortgageAccount.getAccountNo());
			loginResponseDto.setMessage("Logged in Successfully");
			loginResponseDto.setStatus("Success");
			loginResponseDto.setStatusCode(HttpStatus.CREATED.value());
			
			smsService.sms(loginResponseDto.getMessage());

			return loginResponseDto;
		} else {

			throw new InvalidCredentialsException("UserId/Password is incorrect");
		}
	}
}
