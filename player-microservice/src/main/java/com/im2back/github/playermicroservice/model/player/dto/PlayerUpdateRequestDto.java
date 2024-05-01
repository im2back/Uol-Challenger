package com.im2back.github.playermicroservice.model.player.dto;

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
public class PlayerUpdateRequestDto {
	
	private Long id;
	
	private String name;

	private String email;

	private String phone;

	private String alias;

	private Group group;

}
