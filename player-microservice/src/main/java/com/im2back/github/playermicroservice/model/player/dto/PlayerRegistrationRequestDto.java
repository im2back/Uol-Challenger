package com.im2back.github.playermicroservice.model.player.dto;

public record PlayerRegistrationRequestDto(

		String name,

		String email,

		String phone,

		String alias,

		String group

)
{

}
