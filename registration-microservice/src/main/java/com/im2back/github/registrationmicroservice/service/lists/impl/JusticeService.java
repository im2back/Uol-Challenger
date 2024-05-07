package com.im2back.github.registrationmicroservice.service.lists.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.im2back.github.registrationmicroservice.model.entities.ligadajustica.JusticeLeague;
import com.im2back.github.registrationmicroservice.repository.JusticeRepository;
import com.im2back.github.registrationmicroservice.service.lists.interfaces.ListsService;

@Service
public class JusticeService implements ListsService {

	@Autowired
	private JusticeRepository repository;
	
	public void saveNickname(String alias) {
		repository.save(new JusticeLeague(alias));
	}
	
	public List<String> findAllNicknames(){
		return repository.findAll().stream().map(item -> new String(item.getNickName())).collect(Collectors.toList());
	}
	
	public boolean verifyNickname(String nickName){		
		return findAllNicknames().contains(nickName);
	}
}
