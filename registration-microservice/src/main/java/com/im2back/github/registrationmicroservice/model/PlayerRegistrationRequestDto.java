package com.im2back.github.registrationmicroservice.model;

public record PlayerRegistrationRequestDto(
		
		String name,

		String email,

		String phone,

		String alias,

		String group

		) {

}
