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

import com.im2back.github.playermicroservice.model.player.dto.PlayerRegistrationRequestDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerRegistrationResponseDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerUpdateRequestDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerUpdateResponseDto;
import com.im2back.github.playermicroservice.service.PlayerService;

@RestController
@RequestMapping("player")
public class PlayerController {
	
	@Autowired
	private PlayerService service;
	
	
	@GetMapping(value="/list-all")
	public ResponseEntity<List<PlayerRegistrationResponseDto>> listAllplayers(){
		var response = service.findAll();
			return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PlayerRegistrationResponseDto> detailPlayer(@PathVariable Long id){
		var response = service.findById(id);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value="/register")
	public ResponseEntity<PlayerRegistrationResponseDto> registerPlayer(@Valid @RequestBody PlayerRegistrationRequestDto dtoRequest
			,UriComponentsBuilder uriBuilder){
		
		PlayerRegistrationResponseDto response = service.savePlayer(dtoRequest);	
		var uri = uriBuilder.path("/player/{id}").buildAndExpand(response.getId()).toUri();			
			return ResponseEntity.created(uri).body(response);
	}	
	
	@PutMapping(value = "update") 
	public ResponseEntity<PlayerUpdateResponseDto> updatePlayer(@Valid @RequestBody PlayerUpdateRequestDto dtoRequest){
		PlayerUpdateResponseDto response = service.updatePlayer(dtoRequest);
		return ResponseEntity.ok(response);	
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping(value = "delete")
	public ResponseEntity deletePlayer(@RequestParam("id") Long playerId){
		service.deletePlayer(playerId);
		return ResponseEntity.ok(null);	
	}
	

}
