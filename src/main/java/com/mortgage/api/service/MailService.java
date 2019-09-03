package com.mortgage.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.mortgage.api.dto.MailDto;

@Component
public class MailService {

	private JavaMailSender javaMailSender;

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmail(String emailId, String otp) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emailId);
		mail.setSubject("INGTMRW Model Bank");

		mail.setText("The OTP for Making Transfer" + " " + otp);
		javaMailSender.send(mail);
	}

	public void sendEmail(MailDto mailDto) {
	
		
	}

}