package com.im2back.github.registrationmicroservice.service.nickname.factory;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.im2back.github.registrationmicroservice.service.nickname.impl.JusticeAndVingadoresNickListProvider;
import com.im2back.github.registrationmicroservice.service.nickname.impl.JusticeLeagueNickListProvider;
import com.im2back.github.registrationmicroservice.service.nickname.impl.VingadoresNickListProvider;
import com.im2back.github.registrationmicroservice.service.nickname.interfaces.CodinomeList;

@Component
public class CodinomeFactory {

	@Autowired
	private VingadoresNickListProvider vingadoresAliasProvider;

	@Autowired
	private JusticeLeagueNickListProvider justiceLeagueAliasProvider;

	@Autowired
	private JusticeAndVingadoresNickListProvider justiceAndVingadoresAliasProvider;

	private final Map<String, CodinomeList> providerMap = new HashMap<>();

	@PostConstruct
	public void init() {
		providerMap.put("vingadores", vingadoresAliasProvider);
		providerMap.put("justice", justiceLeagueAliasProvider);
		providerMap.put("all", justiceAndVingadoresAliasProvider);
	}

	  public CodinomeList getCodinomeList(String chosenList) {
	        return providerMap.getOrDefault(chosenList, null);
	    }
}