package com.im2back.github.registrationmicroservice.model.entities.validations;

import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationRequestDto;

public interface NickNameValidations {
	
	void valid(PlayerRegistrationRequestDto dto,String param);
}
