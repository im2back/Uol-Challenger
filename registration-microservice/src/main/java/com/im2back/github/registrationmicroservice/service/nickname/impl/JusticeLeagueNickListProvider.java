package com.im2back.github.registrationmicroservice.service.nickname.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.im2back.github.registrationmicroservice.service.nickname.interfaces.CodinomeList;

@Service
public class JusticeLeagueNickListProvider implements CodinomeList{

	@Value("${URLXML}")
	private String url;
		
	
	private final RestTemplate restTemplate;
	
	public JusticeLeagueNickListProvider(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	   public List<String> fetchCodinomes() {
		   String xml = restTemplate.getForObject(this.url, String.class);
	        return parsearCodinomes(xml);
	    }
	   
	   public List<String> parsearCodinomes(String xmlData) {
		    List<String> codinomes = new ArrayList<>();
		    try {
		        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder builder = factory.newDocumentBuilder();
		        InputSource is = new InputSource(new StringReader(xmlData));
		        Document doc = builder.parse(is);
		        doc.getDocumentElement().normalize();

		   
		        NodeList nodeList = doc.getElementsByTagName("codinome");
		        for (int i = 0; i < nodeList.getLength(); i++) {
		            codinomes.add(nodeList.item(i).getTextContent());
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return codinomes;
		}
	
}
