package com.im2back.github.registrationmicroservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationRequestDto;
import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationResponseDto;
import com.im2back.github.registrationmicroservice.service.registration.RegistrationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("register")
public class RegistrationController {

	@Autowired
	private RegistrationService service;
	
	@Operation(summary = "Cadastra um player e retorna um DTO do player cadastrado")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Retorna um DTO de Player em caso de sucesso"),
		@ApiResponse(responseCode = "400", description = "Lança uma exceção do Bean validation em caso de dados cadastrais"
				+ "inválidos ou também pode lançar uma exceção de validação personalizada")})
	@PostMapping
	public ResponseEntity<PlayerRegistrationResponseDto> registerPlayer(@RequestParam String lista,
			@Valid @RequestBody PlayerRegistrationRequestDto dtoRequest) {

		PlayerRegistrationResponseDto response = service.savePlayer(dtoRequest,lista);
		return ResponseEntity.created(null).body(response);
	}
}
