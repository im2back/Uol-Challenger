package com.im2back.github.registrationmicroservice.service.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.im2back.github.registrationmicroservice.service.lists.factory.ListServiceFactory;
import com.im2back.github.registrationmicroservice.service.lists.interfaces.ListsService;
import com.im2back.github.registrationmicroservice.service.nickname.factory.CodinomeFactory;
import com.im2back.github.registrationmicroservice.service.nickname.interfaces.CodinomeList;

@Component
public class NicknameUtil {
	
	 	@Autowired
	    private CodinomeFactory codinomeFactory;
	 	
	 	@Autowired
	    private ListServiceFactory  listServiceFactory;
	 	 	

	    public List<String> loadListOfAliases(String chosenList) {
	        List<String> allNicks = new ArrayList<>();
	        CodinomeList codinomeList = codinomeFactory.getCodinomeList(chosenList);
	        allNicks.addAll(codinomeList.fetchCodinomes());
	        return allNicks;
	    }
	    
	    public void saveToTheListOfNicknamesInUse(String groupName, String nickName) {
			ListsService listService;				
			listService = listServiceFactory.getServiceList(groupName);			
			listService.saveNickname(nickName);
		}
	    
		public List<String> freeNicks(String groupName, String chosenList) {
			List<String> freeNicks = loadListOfAliases(chosenList);
			ListsService listService = listServiceFactory.getServiceList(groupName);
			List<String> nicksInUse =  listService.findAllNicknames();		
			freeNicks.removeAll(nicksInUse);
			return freeNicks;
		}
		
		public String sortNickname(String groupName, String chosenList) {
			List<String> freeNicks = freeNicks(groupName, chosenList);
			Random random = new Random();
			return freeNicks.get(random.nextInt(freeNicks.size()));
		}

}
