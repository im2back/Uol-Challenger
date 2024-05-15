package com.im2back.github.registrationmicroservice.service.registration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.im2back.github.registrationmicroservice.clients.ClientResourcePlayer;
import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationRequestDto;
import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationResponseDto;
import com.im2back.github.registrationmicroservice.model.entities.validations.NickNameValidations;
import com.im2back.github.registrationmicroservice.service.registration.exceptions.ClientConnectionFailedException;
import com.im2back.github.registrationmicroservice.service.registration.exceptions.CustomFeignExceptionBadRequest;
import com.im2back.github.registrationmicroservice.service.utils.NicknameUtil;

import feign.FeignException;


@Service
public class RegistrationService {

	@Autowired
	private List<NickNameValidations> nickNameValidations;

	@Autowired
	private ClientResourcePlayer clientResourcePlayer;

	@Autowired
	private NicknameUtil util;

	@Transactional
	public PlayerRegistrationResponseDto savePlayer(PlayerRegistrationRequestDto dtoRequest, String chosenList) {
		nickNameValidations.forEach(v -> v.valid(dtoRequest, chosenList));
		
		try {
			String alias = util.sortNickname(dtoRequest.getGroup(), chosenList);
			dtoRequest.setAlias(alias);
			ResponseEntity<PlayerRegistrationResponseDto> clientRequestResponse = clientResourcePlayer
					.registerPlayer(dtoRequest);
			return clientRequestResponse.getBody();
			
		} catch (FeignException.BadRequest e) {
			throw new CustomFeignExceptionBadRequest(e.contentUTF8());
		} catch (FeignException.ServiceUnavailable e) {
			throw new ClientConnectionFailedException(e.contentUTF8());
		}
	}

}
