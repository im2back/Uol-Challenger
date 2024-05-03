package com.im2back.github.playermicroservice.service.exceptions;

public class PlayerNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PlayerNotFound(Long id) {
		super("Player não encontrado para o id: "+id); 
	}

}
