package com.mortgage.api.service;

import org.springframework.stereotype.Component;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class SmsService {

	private static final String ACCOUNT_SID = "AC4d8c6bbe148016438fbbba029fcd22fa";
	private static final String AUTH_ID = "e3f404005a838f09403d2eda25317fa9";

	static {
		Twilio.init(ACCOUNT_SID, AUTH_ID);
	}

	public void sms(String message) {

		Message.creator(new PhoneNumber("+916374422314"), new PhoneNumber("+12565107938"), message).create();
	}

}
