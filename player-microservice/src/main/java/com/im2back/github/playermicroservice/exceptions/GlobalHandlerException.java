package com.im2back.github.playermicroservice.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.im2back.github.playermicroservice.service.exceptions.CustomDataIntegrityViolationException;
import com.im2back.github.playermicroservice.service.exceptions.CustomDeletePlayerNotFoundException;
import com.im2back.github.playermicroservice.service.exceptions.PlayerNotFound;

@ControllerAdvice
public class GlobalHandlerException {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardErrorBeanValidation> methodArgumentNotValidException(MethodArgumentNotValidException ex, 
			HttpServletRequest request) {
		
		StandardErrorBeanValidation response = new StandardErrorBeanValidation();
		List<String> messages = new ArrayList<>();
		
		
		   ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
	        	messages.add(fieldError.getField()+" : "+fieldError.getDefaultMessage());
	        });
		
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setError("Bad Request");
		response.setMessage(messages);
		response.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	
	@ExceptionHandler(EntityNotFoundException.class)
	ResponseEntity<StandardError> noSuchElementException(EntityNotFoundException ex, HttpServletRequest request) {
		
		String message = ex.getMessage();
		
		StandardError response = new StandardError();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setError("Not found");
		response.setMessage(message);
		response.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	
	@ExceptionHandler(CustomDeletePlayerNotFoundException.class)
	ResponseEntity<StandardError> customDeletePlayerNotFoundException(CustomDeletePlayerNotFoundException ex, HttpServletRequest request) {
		
		String message = ex.getMessage();
		
		StandardError response = new StandardError();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setError("Not found");
		response.setMessage(message);
		response.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(CustomDataIntegrityViolationException.class)
	ResponseEntity<StandardError> customDataIntegrityViolationException(CustomDataIntegrityViolationException ex, HttpServletRequest request) {
		
		String message = ex.getMessage();
		
		StandardError response = new StandardError();
		response.setStatus(HttpStatus.CONFLICT.value());
		response.setError("Not found");
		response.setMessage(message);
		response.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}
	
	@ExceptionHandler(PlayerNotFound.class)
	ResponseEntity<StandardError> PlayerNotFound(PlayerNotFound ex, HttpServletRequest request) {
		
		String message = ex.getMessage();
		
		StandardError response = new StandardError();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setError("Not found");
		response.setMessage(message);
		response.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
}
