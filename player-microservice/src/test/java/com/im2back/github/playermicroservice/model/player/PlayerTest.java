package com.im2back.github.playermicroservice.model.player;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import utils.Utils;

class PlayerTest {

	@Test
	@DisplayName("Deveria atualizar os dados de um player")
	void update() {
		// ARRANGE
		Player player = Utils.player;

		// ACT
		player.update(Utils.playerUpdateRequestDto);

		// ASSERT
		assertTrue(player.getName() == Utils.playerUpdateRequestDto.getName(),
				"O nome deveria ter sido atualizado de Tony Stark para Tony");

	}

}
