package com.im2back.github.playermicroservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.im2back.github.playermicroservice.model.group.Group;
import com.im2back.github.playermicroservice.model.player.Player;
import com.im2back.github.playermicroservice.model.player.dto.PlayerRegistrationRequestDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerRegistrationResponseDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerUpdateResponseDto;
import com.im2back.github.playermicroservice.repository.PlayerRepository;
import com.im2back.github.playermicroservice.service.exceptions.PlayerNotFound;

import utils.Utils;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

	@Mock
	private PlayerRepository repository;

	@InjectMocks
	private PlayerService service;

	@Captor
	private ArgumentCaptor<Long> idCaptor;

	@Captor
	private ArgumentCaptor<Player> playerCaptor;

	@Mock
	private ModelMapper modelMapper;

	@Test
	@DisplayName("Deveria retornar um objeto do tipo PlayerRegistrationResponseDto de acordo com o id informado")
	void findById() {
		// Arrange
		Long id = 1L;
		BDDMockito.when(repository.findById(id)).thenReturn(Utils.playerOptional);
		BDDMockito.when(modelMapper.map(any(Player.class), eq(PlayerRegistrationResponseDto.class)))
				.thenAnswer(invocation -> Utils.mapPlayerToDto(invocation.getArgument(0)));

		// Act
		PlayerRegistrationResponseDto response = service.findById(id);

		// Assert
		BDDMockito.then(repository).should().findById(idCaptor.capture());
		assertEquals(idCaptor.getValue(), id, "O ID buscado no repositório deve corresponder ao ID do parâmetro");

		assertNotNull(response, "Não deve ser nulo");
		assertEquals(id, response.getId(), "O ID do DTO de resposta deve corresponder ao ID solicitado");

	}

	@Test
	@DisplayName("Deveria retornar uma exceção caso o ID nao exista")
	void findByIdCenario02() {
		// Arrange
		Long id = 1L;
		Optional<Player> player = Optional.ofNullable(null);
		BDDMockito.when(repository.findById(id)).thenReturn(player);

		// ACT & ASSERT
		Assertions.assertThrows(PlayerNotFound.class, () -> {
			service.findById(id);
		});

	}

	@Test
	@DisplayName("Deveria retornar uma lista do tipo List<PlayerRegistrationResponseDto>")
	void findAll() {
		// Arrange
		BDDMockito.when(repository.findAll()).thenReturn(Utils.listPlayer);

		BDDMockito.when(modelMapper.map(any(Player.class), eq(PlayerRegistrationResponseDto.class)))
				.thenAnswer(invocation -> {
					Player player = invocation.getArgument(0);
					return new PlayerRegistrationResponseDto(player.getId(), player.getName(), player.getEmail(),
							player.getPhone(), player.getAlias(), player.getGroup());
				});

		// ACT
		var response = service.findAll();

		// Assert
		verify(repository, times(1)).findAll();
		assertNotNull(response, "A resposta não deve ser nula");
		assertFalse(response.isEmpty(), "A lista não deve estar vazia");
		assertTrue(response.stream().anyMatch(p -> p.getId().equals(1l)), "A lista deve conter o ID 1");
		assertTrue(response.stream().anyMatch(p -> p.getId().equals(2l)), "A lista deve conter o ID 2");
		assertTrue(response.stream().anyMatch(p -> p.getId().equals(3l)), "A lista deve conter o ID 3");

	}

	@Test
	@DisplayName("Deveria retornar uma lista VAZIA do tipo List<PlayerRegistrationResponseDto>")
	void findAllCenario02() {
		// Arrange
		List<Player> playerlist = new ArrayList<>();
		BDDMockito.when(repository.findAll()).thenReturn(playerlist);

		// ACT
		var response = service.findAll();

		// Assert
		verify(repository, times(1)).findAll();
		assertNotNull(response, "A resposta não deve ser nula");
		assertTrue(response.isEmpty(), "A lista deve estar vazia");
	}

	@Test
	@DisplayName("Deveria salvar um player e retornar um objeto do tipo PlayerUpdateResponseDto")
	void savePlayer() {
		// ARRANGE
		BDDMockito.when(modelMapper.map(any(PlayerRegistrationRequestDto.class), eq(Player.class)))
				.thenAnswer(invocation -> Utils.mapDtoToPlayer(invocation.getArgument(0)));

		BDDMockito.when(modelMapper.map(any(Player.class), eq(PlayerRegistrationResponseDto.class)))
				.thenAnswer(invocation -> Utils.mapPlayerToDto(invocation.getArgument(0)));

		// ACT
		PlayerRegistrationResponseDto response = service.savePlayer(Utils.playerRegistrationRequestDto);

		// ASSERT
		BDDMockito.then(repository).should().save(playerCaptor.capture());
		assertEquals(playerCaptor.getValue().getAlias(), Utils.playerRegistrationRequestDto.getAlias(),
				"O codinome ÚNICO do usuário salvo deve ser igual ao codinome do parametro");

		assertEquals(response.getAlias(), Utils.playerRegistrationRequestDto.getAlias(),
				"O codinome ÚNICO do objeto retornado deve ser igual ao codinome do objeto salvo no Banco de dados");

		assertNotNull(response, "A resposta não deve ser nula");
	}

	@Test
	@DisplayName("Deveria atualizar um player e retornar um objeto do tipo PlayerUpdateResponseDto")
	void updatePlayer() {
		// Arrange
		Player mockPlayer = Utils.createMockPlayer();

		BDDMockito.when(repository.findById(any())).thenReturn(Optional.of(mockPlayer));
		BDDMockito.when(modelMapper.map(any(Player.class), eq(PlayerUpdateResponseDto.class)))
				.thenAnswer(invocation -> Utils.mapPlayerToDtoUpdate(invocation.getArgument(0)));

		// ACT
		var response = service.updatePlayer(Utils.playerUpdateRequestDto);

		// Assert
		BDDMockito.then(repository).should().save(playerCaptor.capture());
		assertEquals(playerCaptor.getValue().getId(), Utils.playerUpdateRequestDto.getId());
		assertEquals(Utils.playerUpdateResponseDto.getId(), response.getId());
		verify(mockPlayer, times(1)).update(Utils.playerUpdateRequestDto);
		assertNotNull(response, "A resposta não deve ser nula");

	}

	@Test
	@DisplayName("Deveria excluir o player que contenha o ID do parametro recebido")
	void deletePlayer() {
		// ARRANGE
		Long idPlayer = 1l;

		// ACT
		service.deletePlayer(idPlayer);

		// ASSERT
		BDDMockito.then(repository).should().deleteById(idCaptor.capture());
		assertEquals(idCaptor.getValue(), idPlayer);

	}

	@Test
	@DisplayName("Deveria excluir o player que contenha o ID do parametro recebido")
	void deletePlayerCenario02() {
		// ARRANGE
		Long idPlayer = 1l;

		// ACT
		service.deletePlayer(idPlayer);

		// ASSERT
		BDDMockito.then(repository).should().deleteById(idCaptor.capture());
		assertEquals(idCaptor.getValue(), idPlayer);
	}

	@Test
	@DisplayName("Deveria retornar uma lista do tipo List<String> contendo todos os codinomes do grupo")
	void listCodinomesByGroup() {
		// ARRANGE
		Group group = Group.VINGADORES;

		BDDMockito.when(repository.listCodinomesByGroup(group)).thenReturn(Utils.listCodinomesVingadores);

		// ACT
		var response = service.listCodinomesByGroup(group);

		// ASSERT
		BDDMockito.then(repository).should().listCodinomesByGroup(group);
		assertTrue(response.containsAll(Utils.listCodinomesVingadores),
				"A resposta deve conter todos os codinomes dos Vingadores");
	}

}
