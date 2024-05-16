package com.im2back.github.registrationmicroservice.model.entities.validations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationRequestDto;
import com.im2back.github.registrationmicroservice.service.registration.exceptions.ValidationsCustomExceptions;
import com.im2back.github.registrationmicroservice.service.utils.NicknameUtil;

import util.Utils;

@ExtendWith(MockitoExtension.class)
class ValidNickNameCannotBeDuplicatedTest {

	@Mock
	private NicknameUtil util;

	@InjectMocks
	private ValidNickNameCannotBeDuplicated validNickNameCannotBeDuplicated;

	@Test
	@DisplayName("A validação não deveria lançar a exceção ao receber uma lista com nomes disponiveis")
	void valid() {
		// ARRANGE
		String listaEscolhida = "vingadores";
		PlayerRegistrationRequestDto dto = Utils.playerRegistrationRequestDto;
		BDDMockito.when(util.freeNicks(dto.getGroup(), listaEscolhida)).thenReturn(Utils.listCodinomesVingadores);

		// ACT & ASSERT
		assertDoesNotThrow(() -> validNickNameCannotBeDuplicated.valid(dto, listaEscolhida));
	}

	@Test
	@DisplayName("A validação deveria lançar a exceção ao receber uma lista sem nomes disponiveis")
	void validCenario02() {
		// ARRANGE
		String listaEscolhida = "vingadores";
		PlayerRegistrationRequestDto dto = Utils.playerRegistrationRequestDto;
		BDDMockito.when(util.freeNicks(dto.getGroup(), listaEscolhida)).thenReturn(new ArrayList<>());

		// ACT & ASSERT
		assertThrows(ValidationsCustomExceptions.class,
				() -> validNickNameCannotBeDuplicated.valid(dto, listaEscolhida));

	}

}
