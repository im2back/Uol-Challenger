package com.im2back.github.registrationmicroservice.service.nickname.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.im2back.github.registrationmicroservice.service.nickname.interfaces.CodinomeList;

@Service
public class JusticeAndVingadoresNickListProvider implements CodinomeList {
	
	@Autowired
	private JusticeLeagueNickListProvider justiceProvider;
	
	@Autowired
	private VingadoresNickListProvider vingadoresProvider;

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
