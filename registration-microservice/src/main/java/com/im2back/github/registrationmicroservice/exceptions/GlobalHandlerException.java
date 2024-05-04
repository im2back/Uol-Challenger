package com.im2back.github.registrationmicroservice.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.im2back.github.registrationmicroservice.service.exceptions.ClientConnectionFailedException;
import com.im2back.github.registrationmicroservice.service.exceptions.CustomFeignExceptionBadRequest;

@ControllerAdvice
public class GlobalHandlerException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<StandardErrorBeanValidation> methodArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		StandardErrorBeanValidation response = new StandardErrorBeanValidation();
		List<String> messages = new ArrayList<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(fieldError -> messages.add(fieldError.getField() + ":" + fieldError.getDefaultMessage()));

		response.setError("Bad Request");
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setPath(request.getRequestURI());
		response.setMessage(messages);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(CustomFeignExceptionBadRequest.class)
	ResponseEntity<StandardErrorBeanValidation> feignExceptionBadRequest(CustomFeignExceptionBadRequest ex,
			HttpServletRequest request) throws JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		StandardErrorBeanValidation response = mapper.readValue(ex.getMessage(), StandardErrorBeanValidation.class);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(ClientConnectionFailedException.class)
	ResponseEntity<StandardError> clientConnectionFailedException(ClientConnectionFailedException ex,
			HttpServletRequest request) throws JsonMappingException, JsonProcessingException {

		StandardError response = new StandardError();

		response.setError("Service Unavailable");
		response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
		response.setPath(request.getRequestURI());
		response.setMessage(ex.getMessage());

		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
	}
}
