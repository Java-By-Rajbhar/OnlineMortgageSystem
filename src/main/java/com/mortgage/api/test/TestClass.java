package com.mortgage.api.test;

import java.util.Base64;

public class TestClass {
	
	public static void main(String[] args) {
		String password = "laxman@123";
		String BasicBase64format= Base64.getEncoder().encodeToString(password.getBytes());
		System.out.println("password : "+password);
		System.out.println("BasicBase64format : "+BasicBase64format);
		
		byte[] actualByte= Base64.getDecoder().decode(BasicBase64format);
		String actualString= new String(actualByte);
		System.out.println("actualString : "+actualString);
	}
}
