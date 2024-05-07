package com.im2back.github.registrationmicroservice.service.lists.factory;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.im2back.github.registrationmicroservice.service.lists.impl.AvengersService;
import com.im2back.github.registrationmicroservice.service.lists.impl.JusticeService;
import com.im2back.github.registrationmicroservice.service.lists.interfaces.ListsService;

@Component
public class ListServiceFactory {

	@Autowired
	private AvengersService avengersService;

	@Autowired
	private JusticeService justiceService;
	
	private final Map<String,ListsService> serviceMap = new HashMap<>();
	
	@PostConstruct
	public void init() {
		serviceMap.put("VINGADORES", avengersService);
		serviceMap.put("LIGA_DA_JUSTICA", justiceService);
	}
	
	public ListsService getServiceList(String group) {
		return serviceMap.getOrDefault(group, null);
	}

}
