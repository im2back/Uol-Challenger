package com.im2back.github.registrationmicroservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationRequestDto;
import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationResponseDto;
import com.im2back.github.registrationmicroservice.service.registration.RegistrationService;

import util.Utils;

@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private RegistrationService service;
	
	@Autowired
	private JacksonTester<PlayerRegistrationRequestDto> jacksonPlayerRegistrationRequestDto;
	
	@Autowired
	private JacksonTester<PlayerRegistrationResponseDto> jacksonPlayerRegistrationResponseDto;

	@Test
	@DisplayName("Deveria registrar um jogador")
	void registerPlayer() throws Exception {
		// arrange
		String listParam = "vingadores";
		
		var jsonBody = this.jacksonPlayerRegistrationRequestDto.write(Utils.playerRegistrationRequestDto).getJson();
		var jsonEsperado = this.jacksonPlayerRegistrationResponseDto.write(Utils.playerRegistrationResponseDto).getJson();
		
		BDDMockito.when(service.savePlayer(any(), any())).thenReturn(Utils.playerRegistrationResponseDto);
		// act
		var response =mvc.perform(post("/register?lista="+listParam)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonBody))
				.andReturn().getResponse();
		
		//assert
		assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

	}

}
