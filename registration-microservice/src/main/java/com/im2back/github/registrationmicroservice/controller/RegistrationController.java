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
import com.im2back.github.registrationmicroservice.service.RegistrationService;

@RestController
@RequestMapping("register")
public class RegistrationController {

	@Autowired
	private RegistrationService service;

	@PostMapping
	public ResponseEntity<PlayerRegistrationResponseDto> registerPlayer(@RequestParam String lista,
			@Valid @RequestBody PlayerRegistrationRequestDto dtoRequest) {

		PlayerRegistrationResponseDto response = service.savePlayer(dtoRequest,lista);
		return ResponseEntity.created(service.getLocation()).body(response);
	}
}
