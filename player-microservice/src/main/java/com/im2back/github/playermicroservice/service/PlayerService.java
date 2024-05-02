package com.im2back.github.playermicroservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.im2back.github.playermicroservice.model.player.Player;
import com.im2back.github.playermicroservice.model.player.dto.PlayerRegistrationRequestDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerRegistrationResponseDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerUpdateRequestDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerUpdateResponseDto;
import com.im2back.github.playermicroservice.repository.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository repository;

	@Autowired
	private ModelMapper modelMapper;
	
	
	@Transactional(readOnly = true)
	public PlayerRegistrationResponseDto findById(Long id) {
		Player player = repository.findById(id).get();
		return modelMapper.map(player, PlayerRegistrationResponseDto.class);
	}	
	
	@Transactional(readOnly = true)
	public List<PlayerRegistrationResponseDto> findAll() {
		return repository.findAll().stream()
				.map(element -> modelMapper.map(element, PlayerRegistrationResponseDto.class))
				.collect(Collectors.toList());		
	}	
	
	@Transactional
	public PlayerRegistrationResponseDto savePlayer(PlayerRegistrationRequestDto dtoParam) {
		Player player = modelMapper.map(dtoParam, Player.class);
		repository.save(player);
		return modelMapper.map(player, PlayerRegistrationResponseDto.class);
	}
	
	@Transactional
	public PlayerUpdateResponseDto updatePlayer(PlayerUpdateRequestDto dtoParam){
	    Player loadedPlayer = repository.findById(dtoParam.getId()).get();
	    loadedPlayer.update(dtoParam,loadedPlayer);
		repository.save(loadedPlayer);
		return modelMapper.map(loadedPlayer, PlayerUpdateResponseDto.class);
	}

	@Transactional	
	public void deletePlayer(Long id) {
		repository.deleteById(id);
	}
}
