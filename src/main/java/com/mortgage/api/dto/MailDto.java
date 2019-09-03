package com.mortgage.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MailDto {

	private String emailId;
	private String emailSubject;
	private String text;
}
