package com.mortgage.api.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailService {
	
	
	private JavaMailSender javaMailSender;

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendOTPEmail(String emailId, Long otp) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emailId);
		mail.setSubject("INGTMRW Model Bank");
		
		mail.setText("The OTP for Making Transfer"+" "+otp);
		javaMailSender.send(mail);
	}
	
	public void sendStatusEmail(String emailId, String subject) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emailId);
		mail.setSubject("ING BANk Account verification mail!!");
		
		mail.setText("The Payee for adding Beneficiary is");
		javaMailSender.send(mail);
	}
	
	
}