package com.im2back.github.registrationmicroservice.service.codinome.impl;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.im2back.github.registrationmicroservice.service.codinome.interfaces.CodinomeList;

@Service
public class VingadoresAliasProvider implements CodinomeList{

	@Value("${URLJSON}")
	private String url;

	private final RestTemplate restTemplate;

	public VingadoresAliasProvider(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@SuppressWarnings("unchecked")
	public List<String> fetchCodinomes() {
		String response = restTemplate.getForObject(this.url, String.class);

		JSONObject jsonResponse = new JSONObject(response);
		JSONArray vingadores = jsonResponse.getJSONArray("vingadores");

		return vingadores.toList().stream().map(item -> ((HashMap<String, String>) item).get("codinome"))
				.collect(Collectors.toList());
	}

}
