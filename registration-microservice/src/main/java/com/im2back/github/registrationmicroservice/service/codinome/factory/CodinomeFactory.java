package com.im2back.github.registrationmicroservice.service.codinome.factory;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.im2back.github.registrationmicroservice.service.codinome.impl.JusticeAndVingadoresAliasProvider;
import com.im2back.github.registrationmicroservice.service.codinome.impl.JusticeLeagueAliasProvider;
import com.im2back.github.registrationmicroservice.service.codinome.impl.VingadoresAliasProvider;
import com.im2back.github.registrationmicroservice.service.codinome.interfaces.CodinomeList;

@Component
public class CodinomeFactory {

	@Autowired
	private VingadoresAliasProvider vingadoresAliasProvider;

	@Autowired
	private JusticeLeagueAliasProvider justiceLeagueAliasProvider;

	@Autowired
	private JusticeAndVingadoresAliasProvider justiceAndVingadoresAliasProvider;

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
