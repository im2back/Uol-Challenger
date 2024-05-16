package com.im2back.github.playermicroservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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

import com.im2back.github.playermicroservice.model.group.Group;
import com.im2back.github.playermicroservice.model.player.dto.PlayerRegistrationRequestDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerRegistrationResponseDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerUpdateRequestDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerUpdateResponseDto;
import com.im2back.github.playermicroservice.service.PlayerService;
import com.im2back.github.playermicroservice.service.exceptions.PlayerNotFound;

import utils.Utils;

@AutoConfigureMockMvc
@WebMvcTest(PlayerController.class)
@AutoConfigureJsonTesters
class PlayerControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PlayerService service;

	@Autowired
	private JacksonTester<List<PlayerRegistrationResponseDto>> jacksonListPlayerRegistrationResponseDto;

	@Autowired
	private JacksonTester<PlayerRegistrationResponseDto> jacksonPlayerRegistrationResponseDto;

	@Autowired
	private JacksonTester<List<String>> jacksonListCodinomes;

	@Autowired
	private JacksonTester<PlayerRegistrationRequestDto> jacksonPlayerRegistrationRequestDto;

	@Autowired
	private JacksonTester<PlayerUpdateRequestDto> jacksonPlayerUpdateRequestDto;
	@Autowired
	private JacksonTester<PlayerUpdateResponseDto> jacksonPlayerUpdateResponseDto;

	@Test
	@DisplayName("Deveria retornar Status: 200 OK e uma lista do Tipo : PlayerRegistrationResponseDto")
	void listAllplayers() throws Exception {
		// ARRANGE
		BDDMockito.when(service.findAll()).thenReturn(Utils.listPlayerRegistrationResponseDto);

		var jsonEsperado = this.jacksonListPlayerRegistrationResponseDto.write(Utils.listPlayerRegistrationResponseDto)
				.getJson();

		// ACT
		var response = mvc.perform(get("/player/list-all")).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(3)).andExpect(jsonPath("$[0].name").value("Tony Stark"))
				.andReturn().getResponse();

		// ASSERT
		verify(service, times(1)).findAll();
		assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
	}

	@Test
	@DisplayName("Deveria retornar Status: 200 OK e um objeto do Tipo : PlayerRegistrationResponseDto")
	void detailPlayer() throws Exception {
		// ARRANGE
		Long id = 1L;
		var jsonEsperado = this.jacksonPlayerRegistrationResponseDto.write(Utils.playerRegistrationResponseDto)
				.getJson();
		BDDMockito.when(service.findById(id)).thenReturn(Utils.playerRegistrationResponseDto);

		// ACT + ASSERT
		var response = mvc.perform(get("/player/{id}", id)).andExpect(status().isOk()).andReturn().getResponse();

		// ASSERT
		verify(service, times(1)).findById(id);
		assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
	}

	@Test
	@DisplayName("Deveria retornar Status: 400 e uma exceção PlayerNotFoundId ao buscar por um id inexistente")
	void detailPlayerCenario02() throws Exception {
		// ARRANGE
		Long id = 1L;
		BDDMockito.when(service.findById(id)).thenThrow(new PlayerNotFound(id));

		// ACT + ASSERT
		mvc.perform(get("/player/{id}", id)).andExpect(status().isNotFound()).andReturn().getResponse();

		// ASSERT
		verify(service, times(1)).findById(id);
	}

	@Test
	@DisplayName("Deveria retornar Status: 200 e um lista de codinomes")
	void list() throws Exception {
		// ARRANGE
		BDDMockito.when(service.listCodinomesByGroup(Group.VINGADORES)).thenReturn(Utils.listCodinomesVingadores);
		var jsonEsperado = this.jacksonListCodinomes.write(Utils.listCodinomesVingadores).getJson();

		// ACT + ASSERT
		var response = mvc.perform(get("/player/list-codenames/{group}", Group.VINGADORES)).andExpect(status().isOk())
				.andReturn().getResponse();

		// ASSERT
		verify(service, times(1)).listCodinomesByGroup(Group.VINGADORES);
		assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
	}

	@Test
	@DisplayName("Deveria retornar o Status 201 e um DTO do tipo PlayerRegistrationResponseDto")
	void registerPlayer() throws Exception {

		// ARRANGE
		var jsonEsperado = this.jacksonPlayerRegistrationResponseDto.write(Utils.playerRegistrationResponseDto)
				.getJson();
		var jsonBody = this.jacksonPlayerRegistrationRequestDto.write(Utils.playerRegistrationRequestDto).getJson();
		BDDMockito.when(service.savePlayer(Utils.playerRegistrationRequestDto))
				.thenReturn(Utils.playerRegistrationResponseDto);

		// ACT + ASSERT
		var response = mvc.perform(post("/player/register").contentType(MediaType.APPLICATION_JSON).content(jsonBody))
				.andExpect(status().isCreated()).andReturn().getResponse();

		// ASSERT
		verify(service, times(1)).savePlayer(Utils.playerRegistrationRequestDto);
		assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

	}

	@Test
	@DisplayName("Deveria retonar o status 200ok e um objeto do tipo PlayerUpdateResponseDto")
	void updatePlayer() throws Exception {
		// ARRANGE
		var jsonBody = this.jacksonPlayerUpdateRequestDto.write(Utils.playerUpdateRequestDto).getJson();
		var jsonEsperado = this.jacksonPlayerUpdateResponseDto.write(Utils.playerUpdateResponseDto).getJson();
		BDDMockito.when(service.updatePlayer(Utils.playerUpdateRequestDto)).thenReturn(Utils.playerUpdateResponseDto);

		// ACT
		var response = mvc.perform(put("/player/update").contentType(MediaType.APPLICATION_JSON).content(jsonBody))
				.andExpect(status().isOk()).andReturn().getResponse();

		// ASSERT
		verify(service, times(1)).updatePlayer(Utils.playerUpdateRequestDto);
		assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
	}

	@Test
	@DisplayName("Deveria retornar o Status 200ok após excluir um usuário")
	void deletePlayer() throws Exception {
		// ARRANGE
		Long id = 1l;

		// ACT + ASSERT
		mvc.perform(delete("/player/delete?id=" + id)).andExpect(status().isOk());

		// ASSERT
		verify(service, times(1)).deletePlayer(id);
	}

}
