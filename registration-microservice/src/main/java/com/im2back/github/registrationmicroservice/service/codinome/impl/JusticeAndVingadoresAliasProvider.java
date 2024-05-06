package com.im2back.github.registrationmicroservice.service.codinome.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.im2back.github.registrationmicroservice.service.codinome.interfaces.CodinomeList;

@Service
public class JusticeAndVingadoresAliasProvider implements CodinomeList {
	
	@Autowired
	private JusticeLeagueAliasProvider justiceProvider;
	
	@Autowired
	private VingadoresAliasProvider vingadoresProvider;

	@Override
	public List<String> fetchCodinomes() {
		List<String> justiceList = justiceProvider.fetchCodinomes();
		List<String> vingadoresList = vingadoresProvider.fetchCodinomes();
		List<String> allAliasesList = new ArrayList<>();
		allAliasesList.addAll(justiceList);
		allAliasesList.addAll(vingadoresList);
		return allAliasesList;
	}
	
	
}
