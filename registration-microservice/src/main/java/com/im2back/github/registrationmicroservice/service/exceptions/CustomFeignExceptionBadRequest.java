package com.im2back.github.registrationmicroservice.service.exceptions;

public class CustomFeignExceptionBadRequest extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomFeignExceptionBadRequest(String content) {
		super(content);
	}
}
