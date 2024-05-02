package com.im2back.github.registrationmicroservice.clients;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClienteResourceJusticeLeagueAliasList {

	@Value("${URLXML}")
	private String url;
		
	
	private final RestTemplate restTemplate;
	
	public ClienteResourceJusticeLeagueAliasList(RestTemplate restTemplate) {
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

		        // Obtendo todos os elementos <codinome>
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
