package com.im2back.github.registrationmicroservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.im2back.github.registrationmicroservice.model.vingadores.Avengers;
import com.im2back.github.registrationmicroservice.repository.AvengersRepository;

@Service
public class AvengersService {

	@Autowired
	private AvengersRepository repository;
	
	public void saveNickname(String alias) {
		repository.save(new Avengers(alias));
	}
	
	public List<String> findAllNicknames(){
		return repository.findAll().stream().map(item -> new String(item.getAlias())).collect(Collectors.toList());
	}
	
	public boolean verifyNickname(String nickName){		
		return findAllNicknames().contains(nickName);
	}
}
