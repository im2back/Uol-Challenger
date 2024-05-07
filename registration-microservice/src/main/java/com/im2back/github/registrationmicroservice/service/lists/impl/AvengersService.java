package com.im2back.github.registrationmicroservice.service.lists.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.im2back.github.registrationmicroservice.model.entities.vingadores.Avengers;
import com.im2back.github.registrationmicroservice.repository.AvengersRepository;
import com.im2back.github.registrationmicroservice.service.lists.interfaces.ListsService;

@Service
public class AvengersService implements ListsService{

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
