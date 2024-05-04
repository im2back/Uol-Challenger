package com.im2back.github.playermicroservice.service.exceptions;

public class CustomDeletePlayerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomDeletePlayerNotFoundException(Long id) {
	        super("Jogador com ID: " +  id  + " não existe.");
	    }

}
