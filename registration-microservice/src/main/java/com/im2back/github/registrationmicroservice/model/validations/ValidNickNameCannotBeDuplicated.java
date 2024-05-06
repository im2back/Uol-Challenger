package com.im2back.github.registrationmicroservice.model.validations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationRequestDto;
import com.im2back.github.registrationmicroservice.service.codinome.impl.JusticeLeagueAliasProvider;
import com.im2back.github.registrationmicroservice.service.codinome.impl.VingadoresAliasProvider;
import com.im2back.github.registrationmicroservice.service.lists.AvengersService;
import com.im2back.github.registrationmicroservice.service.lists.JusticeService;
import com.im2back.github.registrationmicroservice.service.registration.exceptions.ValidationsCustomExceptions;

@Component
public class ValidNickNameCannotBeDuplicated implements NickNameValidations {

	@Autowired
	private AvengersService avengersService;

	@Autowired
	private JusticeService justiceService;

	@Autowired
	private VingadoresAliasProvider clientGetVingadoresAlias;

	@Autowired
	private JusticeLeagueAliasProvider clienteGetJusticeLeagueAlias;

	@Override
	public void valid(PlayerRegistrationRequestDto dto, String listaEscolhidaParam) {

		if (dto.getGroup().equals("VINGADORES")) {

			List<String> nicksInUse = avengersService.findAllNicknames();
			List<String> listaEscolhida = new ArrayList<>();

			if (listaEscolhidaParam.equals("vingadores")) {
				listaEscolhida = clientGetVingadoresAlias.fetchCodinomes();
			}

			if (listaEscolhidaParam.equals("justice")) {
				listaEscolhida = clienteGetJusticeLeagueAlias.fetchCodinomes();
			}

			if (listaEscolhidaParam.equals("all")) {
				listaEscolhida = allNicks();
			}

			if (nicksInUse.containsAll(listaEscolhida)) {
				throw new ValidationsCustomExceptions(
						"Grupo Vingadores não possui codinomes disponiveis para a lista: " + listaEscolhidaParam);
			}
		}

		if (dto.getGroup().equals("LIGA_DA_JUSTICA")) {

			List<String> nicksInUse = justiceService.findAllNicknames();
			List<String> listaEscolhida = new ArrayList<>();

			if (listaEscolhidaParam.equals("vingadores")) {
				listaEscolhida = clientGetVingadoresAlias.fetchCodinomes();
			}

			if (listaEscolhidaParam.equals("justice")) {
				listaEscolhida = clienteGetJusticeLeagueAlias.fetchCodinomes();
			}

			if (listaEscolhidaParam.equals("all")) {
				listaEscolhida = allNicks();
			}

			if (nicksInUse.containsAll(listaEscolhida)) {
				throw new ValidationsCustomExceptions(
						"Grupo Liga da Justica não possui codinomes disponiveis para a lista: " + listaEscolhidaParam);
			}
		}

	}

	private List<String> allNicks() {
		List<String> allNicks = new ArrayList<>();
		allNicks.addAll(clientGetVingadoresAlias.fetchCodinomes());
		allNicks.addAll(clienteGetJusticeLeagueAlias.fetchCodinomes());

		return allNicks;
	}
}
