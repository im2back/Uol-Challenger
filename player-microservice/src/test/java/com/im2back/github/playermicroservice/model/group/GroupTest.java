package com.im2back.github.playermicroservice.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GroupTest {

	@Test
	@DisplayName("Deveria retornar o enum correspondente a string recebido como parâmetro")
	void convertStringInGroup() {
		// ARRANGE
		String param = "VINGADORES";

		// ACT

		var response = Group.convertStringInGroup(param);

		// ASSERT
		assertEquals(Group.VINGADORES, response, "Deveria retornar o grupo vingadores");
	}

	@Test
	@DisplayName("Deveria retornar o enum correspondente a string recebido como parâmetro")
	void convertStringInGroupCenario02() {
		// ARRANGE
		String param = "LIGA_DA_JUSTICA";

		// ACT

		var response = Group.convertStringInGroup(param);

		// ASSERT
		assertEquals(Group.LIGA_DA_JUSTICA, response, "Deveria retornar o grupo LIGA_DA_JUSTICA");
	}
	
	@Test
	@DisplayName("Deveria retornar o enum correspondente a string recebido como parâmetro")
	void convertStringInGroupCenario03() {
		// ARRANGE
		String param = "GRUPO_INEXISTENTE";

		// ACT

	Assertions.assertThrows(IllegalArgumentException.class, () -> Group.convertStringInGroup(param));
	}
	
	

}
