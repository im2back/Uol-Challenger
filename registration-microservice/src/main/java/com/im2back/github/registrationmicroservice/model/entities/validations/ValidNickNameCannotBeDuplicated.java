package com.im2back.github.registrationmicroservice.model.entities.validations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationRequestDto;
import com.im2back.github.registrationmicroservice.service.registration.exceptions.ValidationsCustomExceptions;
import com.im2back.github.registrationmicroservice.service.utils.NicknameUtil;

@Component
public class ValidNickNameCannotBeDuplicated implements NickNameValidations {


	
	@Autowired
	private NicknameUtil util;

	@Override
	public void valid(PlayerRegistrationRequestDto dto, String listaEscolhidaParam) {
		
		List<String> freeNicks = util.freeNicks(dto.getGroup(), listaEscolhidaParam);
		
		if(freeNicks.isEmpty()) {
			throw new ValidationsCustomExceptions("Não há codinomes disponiveis na lista " +listaEscolhidaParam +
					" para serem cadastrados no grupo "+ dto.getGroup());
		}

	}


}
