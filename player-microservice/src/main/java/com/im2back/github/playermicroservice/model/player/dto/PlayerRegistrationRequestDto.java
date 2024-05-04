package com.im2back.github.playermicroservice.model.player.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.im2back.github.playermicroservice.model.group.Group;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PlayerRegistrationRequestDto{
	
	@NotBlank(message = "O nome não pode estar em branco")
	private String name;
	
	@NotBlank(message = "O e-mail não pode estar em branco")
	private String email;
	
	@Size(min = 11, max = 11, message = "O campo phone deve conter 11 caracteres.")
	private String phone;
	
	private String alias;
	private Group group;
	
}
