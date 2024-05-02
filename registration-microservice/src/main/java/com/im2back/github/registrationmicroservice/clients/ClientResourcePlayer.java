package com.im2back.github.registrationmicroservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationRequestDto;
import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationResponseDto;

//@FeignClient(name = "player-ms", path = "/player")
@FeignClient(name = "player-ms", path = "/player")
public interface ClientResourcePlayer {
	
	  @PostMapping("/register")
	    ResponseEntity<PlayerRegistrationResponseDto> registerPlayer(@RequestBody PlayerRegistrationRequestDto playerRequest);
	
}
