package com.im2back.github.registrationmicroservice.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationRequestDto;
import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationResponseDto;


@FeignClient(name = "player-ms", path = "/player")
public interface ClientResourcePlayer {
	
	  @PostMapping("/register")
	  ResponseEntity<PlayerRegistrationResponseDto> registerPlayer(@RequestBody PlayerRegistrationRequestDto playerRequest);
	  
	  @GetMapping("/list-codenames/{group}")
	    ResponseEntity<List<String>> list(@PathVariable("group") String group);
	  
	
}
