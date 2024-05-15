package com.im2back.github.registrationmicroservice.service.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.im2back.github.registrationmicroservice.clients.ClientResourcePlayer;
import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationRequestDto;
import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationResponseDto;
import com.im2back.github.registrationmicroservice.model.entities.validations.NickNameValidations;
import com.im2back.github.registrationmicroservice.service.utils.NicknameUtil;

import util.Utils;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

	@Spy
	private List<NickNameValidations> nickNameValidations= new ArrayList<>();
	
	@Mock
	private NickNameValidations valid01;
	
	@Mock
	private NickNameValidations valid02;

	@Mock
	private ClientResourcePlayer clientResourcePlayer;

	@Mock
	private NicknameUtil util;

	@InjectMocks
	private RegistrationService service;
	
	@Captor
	private ArgumentCaptor<PlayerRegistrationRequestDto> captorPlayerRegistrationRequestDto;

	@Test
	@DisplayName("Deveria fazer uma requisição para registrar um player  e retornar um PlayerRegistrationResponseDto")
	void savePlayer() {
		// ARRANGE
		nickNameValidations.add(valid01);
		nickNameValidations.add(valid02);
		
		BDDMockito.when(this.util.sortNickname(any(), any())).thenReturn("Hulk");
	
		String listaEscolhida = "vingadores";	
		PlayerRegistrationRequestDto dto = Utils.playerRegistrationRequestDto;
		
		ResponseEntity<PlayerRegistrationResponseDto> responseEntity = ResponseEntity.ok(Utils.playerRegistrationResponseDto);
		BDDMockito.when(this.clientResourcePlayer.registerPlayer(any(PlayerRegistrationRequestDto.class)))
        .thenReturn(responseEntity);
		
		// ACT
		var response = service.savePlayer(dto,listaEscolhida);
			
		//ASSERT
		BDDMockito.then(clientResourcePlayer).should().registerPlayer(this.captorPlayerRegistrationRequestDto.capture());
		assertEquals(captorPlayerRegistrationRequestDto.getValue().getAlias(), "Hulk");
		assertEquals(captorPlayerRegistrationRequestDto.getValue().getName(), dto.getName());
		assertEquals(captorPlayerRegistrationRequestDto.getValue().getGroup(), dto.getGroup());
		
		BDDMockito.then(valid01).should().valid(dto, listaEscolhida);  
		BDDMockito.then(valid02).should().valid(dto, listaEscolhida);
		
		verify(util,times(1)).sortNickname("VINGADORES", listaEscolhida);
		
		assertEquals(response.getId(),Utils.playerRegistrationResponseDto.getId());
	}

}
