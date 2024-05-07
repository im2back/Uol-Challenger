package com.im2back.github.registrationmicroservice.service.registration.exceptions;

public class ValidationsCustomExceptions extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ValidationsCustomExceptions (String msg) {
		super(msg);
	}

}
