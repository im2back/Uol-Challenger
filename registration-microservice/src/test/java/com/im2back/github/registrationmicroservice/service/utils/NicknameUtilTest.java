package com.im2back.github.registrationmicroservice.service.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.im2back.github.registrationmicroservice.clients.ClientResourcePlayer;
import com.im2back.github.registrationmicroservice.service.nickname.factory.CodinomeFactory;
import com.im2back.github.registrationmicroservice.service.nickname.impl.VingadoresNickListProvider;

import util.Utils;

@ExtendWith(MockitoExtension.class)
class NicknameUtilTest {

	@Mock
	private CodinomeFactory codinomeFactory;

	@Mock
	private ClientResourcePlayer clienteResourcePlayer;

	@InjectMocks
	private NicknameUtil nicknameUtil;

	@Mock
	private VingadoresNickListProvider vingadoresAliasProvider;

	@Test
	@DisplayName("Deveria retornar uma lista de codinomes referentes ao nome da lista")
	void loadListOfAliases() {
		// ARRANGE
		String chosenList = "vingadores";
		List<String> list = Utils.listCodinomesVingadores;

		BDDMockito.when(codinomeFactory.getCodinomeList(chosenList)).thenReturn(vingadoresAliasProvider);
		BDDMockito.when(vingadoresAliasProvider.fetchCodinomes()).thenReturn(list);
		
		// ACT
		var response = nicknameUtil.loadListOfAliases(chosenList);

		// ASSERT
		BDDMockito.then(this.codinomeFactory).should().getCodinomeList(chosenList);
		assertTrue(response.containsAll(list));
	}

	@Test
	@DisplayName("Deveria retornar uma lista de codinomes disponiveis")
	void freeNicks() {
		// ARRANGE
		String chosenList = "vingadores";
		String groupName = "VINGADORES";

		ResponseEntity<List<String>> responseRequest = ResponseEntity.ok(Utils.codinomesEmUso);
		BDDMockito.when(clienteResourcePlayer.list(groupName)).thenReturn(responseRequest);

		BDDMockito.when(codinomeFactory.getCodinomeList(chosenList)).thenReturn(vingadoresAliasProvider);
		BDDMockito.when(nicknameUtil.loadListOfAliases(chosenList)).thenReturn(Utils.listCodinomesVingadores);

		// ACT
		var response = nicknameUtil.freeNicks(groupName, chosenList);

		// ASSERT
		assertFalse(response.containsAll(Utils.listCodinomesVingadores));
	}

	@Test
	@DisplayName("Deveria retornar uma lista de codinomes disponiveis")
	void sortNickname() {
		// ARRANGE
		String chosenList = "vingadores";
		String groupName = "VINGADORES";
		List<String> assertlist = Arrays.asList("Thor", "Feiticeira Escarlate", "Visao");

		ResponseEntity<List<String>> responseRequest = ResponseEntity.ok(Utils.codinomesEmUso);
		BDDMockito.when(clienteResourcePlayer.list(groupName)).thenReturn(responseRequest);

		BDDMockito.when(codinomeFactory.getCodinomeList(chosenList)).thenReturn(vingadoresAliasProvider);
		BDDMockito.when(nicknameUtil.loadListOfAliases(chosenList)).thenReturn(Utils.listCodinomesVingadores);

		// ACT
		var response = nicknameUtil.sortNickname(groupName, chosenList);

		// ASSERT
		assertTrue(assertlist.contains(response));

	}

}
