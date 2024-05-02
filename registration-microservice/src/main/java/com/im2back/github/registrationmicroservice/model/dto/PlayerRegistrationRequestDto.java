package com.im2back.github.registrationmicroservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PlayerRegistrationRequestDto{
	private String name;

	private String email;

	private String phone;
	
	private String alias;

	private String group;

}
