package com.mortgage.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mortgage.api.entity.Account;
import com.mortgage.api.entity.TransactionSummary;
import com.mortgage.api.repository.AccountRepository;
import com.mortgage.api.repository.TransactionSummaryRepository;
import com.mortgage.api.repository.UserRepository;

/**
 * @author Laxman
 * @desc This service will take care of all transaction from transaction account
 *       to mortgage account
 *
 */
@Service
public class SchedulerServiceImpl implements SchedulerService {

	private final static Logger LOGGER = LoggerFactory.getLogger(SchedulerServiceImpl.class);
	
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TransactionSummaryRepository txnRepository;

	/**
	 * This method will take 
	 */
	@Override
	public void runTask() {
		
		LOGGER.info("SchedulerServiceImpl :: runTask - starts");
		
		List<String> userIds = userRepository.getDistinctUserId();
		userIds.stream().forEach(userId -> {
			
			LOGGER.info("SchedulerServiceImpl :: runTask - userId : "+userId);
			
			Account mortgageAccount = accountRepository.findByAccountTypeAndUserId("Mortgage", userId);
			Account transactionAccount = accountRepository.findByAccountTypeAndUserId("Transaction", userId);
			mortgageAccount.setAmount(mortgageAccount.getAmount() + 200);
			transactionAccount.setAmount(transactionAccount.getAmount() - 200);

			TransactionSummary mortgageTransaction = new TransactionSummary();
			mortgageTransaction.setAccountNo(mortgageAccount.getAccountNo());
			mortgageTransaction.setAmount(200.0);
			mortgageTransaction.setDescription("Mortgage Account Credit.");
			mortgageTransaction.setTransactionDateTime(LocalDateTime.now());
			mortgageTransaction.setTransactionType("CR");

			TransactionSummary accountTransaction = new TransactionSummary();
			accountTransaction.setAccountNo(transactionAccount.getAccountNo());
			accountTransaction.setAmount(200.0);
			accountTransaction.setDescription("Transaction Account Debit.");
			accountTransaction.setTransactionDateTime(LocalDateTime.now());
			accountTransaction.setTransactionType("DR");

			accountRepository.save(mortgageAccount);
			accountRepository.save(transactionAccount);

			txnRepository.save(mortgageTransaction);
			txnRepository.save(accountTransaction);

		});
		
		LOGGER.info("SchedulerServiceImpl :: runTask - END");
	}
}
