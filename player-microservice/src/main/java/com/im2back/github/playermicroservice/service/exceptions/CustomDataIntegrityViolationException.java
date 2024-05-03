package com.im2back.github.playermicroservice.service.exceptions;

public class CustomDataIntegrityViolationException extends RuntimeException{
	

	private static final long serialVersionUID = 1L;

	public CustomDataIntegrityViolationException(Long id) {
		super("Não é possível excluir o jogador com ID: " + id + " porque ele está referenciado por outros registros.");
	}

}
