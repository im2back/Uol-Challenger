package com.im2back.github.registrationmicroservice.service.lists.interfaces;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface ListsService {

	public void saveNickname(String alias);
	
	public List<String> findAllNicknames();
	
	public boolean verifyNickname(String nickName);

}
