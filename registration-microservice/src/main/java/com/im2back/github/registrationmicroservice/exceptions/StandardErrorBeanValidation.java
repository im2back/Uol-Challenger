package com.im2back.github.registrationmicroservice.exceptions;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardErrorBeanValidation {

	private Integer status;
	private String error;
	private List<String> message;
	private String path;
}
