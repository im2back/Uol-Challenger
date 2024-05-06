package com.im2back.github.registrationmicroservice.service.registration;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.im2back.github.registrationmicroservice.clients.ClientResourcePlayer;
import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationRequestDto;
import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationResponseDto;
import com.im2back.github.registrationmicroservice.model.validations.NickNameValidations;
import com.im2back.github.registrationmicroservice.service.codinome.factory.CodinomeFactory;
import com.im2back.github.registrationmicroservice.service.codinome.interfaces.CodinomeList;
import com.im2back.github.registrationmicroservice.service.lists.AvengersService;
import com.im2back.github.registrationmicroservice.service.lists.JusticeService;
import com.im2back.github.registrationmicroservice.service.registration.exceptions.ClientConnectionFailedException;
import com.im2back.github.registrationmicroservice.service.registration.exceptions.CustomFeignExceptionBadRequest;

import feign.FeignException;
import lombok.Getter;

@Service
public class RegistrationService {
	
	@Autowired
	private List<NickNameValidations> nickNameValidations;

	@Autowired
	private ClientResourcePlayer clientResourcePlayer;
	
	@Autowired
	private AvengersService avengersService;
	
	@Autowired
	private  CodinomeFactory codinomeFactory;
	
	@Autowired
	private JusticeService justiceService;
	
	@Getter
	URI location;

	@Transactional
	public PlayerRegistrationResponseDto savePlayer(PlayerRegistrationRequestDto dtoRequest, String chosenList) {
		nickNameValidations.forEach(v -> v.valid(dtoRequest,chosenList));
		try {
			String alias = sortNickname(dtoRequest.getGroup(),chosenList);
			dtoRequest.setAlias(alias);
			saveNick(dtoRequest.getGroup(), alias);
			ResponseEntity<PlayerRegistrationResponseDto> clientDataResponse = clientResourcePlayer.registerPlayer(dtoRequest);
			
			HttpHeaders headers = clientDataResponse.getHeaders();
			location = headers.getLocation();
			
			return clientDataResponse.getBody();
		}
		catch (FeignException.BadRequest e) {
			throw new CustomFeignExceptionBadRequest(e.contentUTF8());
		}	
		catch (FeignException.ServiceUnavailable e) {
			throw new ClientConnectionFailedException(e.contentUTF8());
		}
	}

	public String sortNickname(String groupName, String chosenList) {
		List<String> freeNicks = freeNicks(groupName,chosenList);
		Random random = new Random();
		return freeNicks.get(random.nextInt(freeNicks.size()));
	}

	private List<String> freeNicks(String groupName, String chosenList) {
		List<String> freeNicks = loadListOfAliases(chosenList);		
		List<String> nicksInUse = new ArrayList<>();
		
		if(groupName.equals("VINGADORES")) {
			nicksInUse = avengersService.findAllNicknames();
		}		
		if(groupName.equals("LIGA_DA_JUSTICA")) {
			nicksInUse = justiceService.findAllNicknames();
		}	
		freeNicks.removeAll(nicksInUse);
		return freeNicks;
	}

	private List<String> loadListOfAliases(String chosenList) {
		List<String> allNicks = new ArrayList<>();
		CodinomeList codinomeList = codinomeFactory.getCodinomeList(chosenList);
		allNicks.addAll(codinomeList.fetchCodinomes());	
		return allNicks;
	}
	
	private void saveNick(String group, String alias) {
		
		if(group.equals("VINGADORES")) {
			avengersService.saveNickname(alias);
		}		
		if(group.equals("LIGA_DA_JUSTICA")) {
			justiceService.saveNickname(alias);
		}	
	}

}
