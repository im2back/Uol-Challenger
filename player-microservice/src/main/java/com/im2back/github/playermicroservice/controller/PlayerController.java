package com.im2back.github.playermicroservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.im2back.github.playermicroservice.model.group.Group;
import com.im2back.github.playermicroservice.model.player.dto.PlayerRegistrationRequestDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerRegistrationResponseDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerUpdateRequestDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerUpdateResponseDto;
import com.im2back.github.playermicroservice.service.PlayerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("player")
public class PlayerController {

	@Autowired
	private PlayerService service;

	@Operation(summary = "Retorna uma lista contendo DTO'S de Players cadastrados")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Retorna a lista de Players")})
	@GetMapping(value = "/list-all")
	public ResponseEntity<List<PlayerRegistrationResponseDto>> listAllplayers() {
		var response = service.findAll();
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Retorna um DTO de Player, apartir de um ID")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Retorna um DTO de Player"),
			@ApiResponse(responseCode = "400", description = "Foi gerada uma exceção do tipo  PlayerNotFound")})
	@GetMapping(value = "/{id}")
	public ResponseEntity<PlayerRegistrationResponseDto> detailPlayer(@PathVariable Long id) {
		var response = service.findById(id);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Registra um Player e retorna um DTO de Player")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "Retorna um DTO do Player cadastrado"),
			@ApiResponse(responseCode = "400", description = "Foi gerada uma exceção pelo Bean validation /"
					+ " Validações personalizadas relacionadas as regras de cadastro")})
	@PostMapping(value = "/register")
	public ResponseEntity<PlayerRegistrationResponseDto> registerPlayer(
			@Valid @RequestBody PlayerRegistrationRequestDto dtoRequest, UriComponentsBuilder uriBuilder) {

		PlayerRegistrationResponseDto response = service.savePlayer(dtoRequest);
		var uri = uriBuilder.path("/player/{id}").buildAndExpand(response.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}

	@Operation(summary = "Atualiza dados de um Player e Retorna um DTO do Player atualizado")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Retorna um DTO do Player atualizado"),
			@ApiResponse(responseCode = "400", description = "Foi gerada uma exceção pelo Bean validation /"
					+ " Validações personalizadas relacionadas as regras de cadastro")})
	@PutMapping(value = "update")
	public ResponseEntity<PlayerUpdateResponseDto> updatePlayer(@Valid @RequestBody PlayerUpdateRequestDto dtoRequest) {
		PlayerUpdateResponseDto response = service.updatePlayer(dtoRequest);
		return ResponseEntity.ok(response);
	}
		
	@Operation(summary = "Delete um Player da base de dados com base em um ID")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Deleta o player e não retorna nada"),
			@ApiResponse(responseCode = "400", description = "Retorna uma exceção ao tentar excluir um player"
					+ "que não existe"),
			@ApiResponse(responseCode = "409", description = "Retorna uma exceção ao tentar excluir um player "
					+ "referenciados por outras tabelas")})
	@DeleteMapping(value = "delete")
	public ResponseEntity<Void> deletePlayer(@RequestParam("id") Long playerId) {
		service.deletePlayer(playerId);
		return ResponseEntity.ok().build();
	}
	
	@Operation(summary = "Retorna uma lista contendos os codinomes em uso por determinado GRUPO")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna a lista de codinomes baseados num GRUPO")})
	@GetMapping("/list-codenames/{group}")
	ResponseEntity<List<String>> list(@PathVariable("group") String group) {
		var response = service.listCodinomesByGroup(Group.convertStringInGroup(group));
		return ResponseEntity.ok(response);
	}

}
