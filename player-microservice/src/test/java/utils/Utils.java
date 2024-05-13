package utils;

import java.util.ArrayList;
import java.util.List;

import com.im2back.github.playermicroservice.model.group.Group;
import com.im2back.github.playermicroservice.model.player.dto.PlayerRegistrationRequestDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerRegistrationResponseDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerUpdateRequestDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerUpdateResponseDto;

public class Utils {

	public static List<PlayerRegistrationResponseDto> listPlayerRegistrationResponseDto = new ArrayList<>();
	
	public static PlayerRegistrationRequestDto playerRegistrationRequestDto = new PlayerRegistrationRequestDto("Tony Stark", 
			"tony@stark.com", "11234567890", "Hulk", Group.VINGADORES);
	public static PlayerRegistrationResponseDto playerRegistrationResponseDto = new PlayerRegistrationResponseDto(1L,
			"Tony Stark", "tony@stark.com", "11234567890", "Hulk", Group.VINGADORES);
	
	public static PlayerUpdateRequestDto playerUpdateRequestDto = new PlayerUpdateRequestDto  (1L,
			"Tony Stark", "tony@stark.com", "11234567890", "Hulk", Group.VINGADORES);
	
	public static PlayerUpdateResponseDto playerUpdateResponseDto = new PlayerUpdateResponseDto  (1L,
			"Tony Stark", "tony@stark.com", "11234567890", "Hulk", Group.VINGADORES);
	
	
	public static List<String> listCodinomesVingadores = new ArrayList<>();
	public static List<String> listCodinomesLiga = new ArrayList<>();

	static {

		listPlayerRegistrationResponseDto.add(new PlayerRegistrationResponseDto(1L, "Tony Stark", "tony@stark.com",
				"11234567890", "Hulk", Group.VINGADORES));
		listPlayerRegistrationResponseDto.add(new PlayerRegistrationResponseDto(2L, "Bruce Wayne", "bruce@prises.com",
				"10987654321", "Batman", Group.LIGA_DA_JUSTICA));
		listPlayerRegistrationResponseDto.add(new PlayerRegistrationResponseDto(3L, "Clark Kent", "clark@planet.com",
				"11122231333", "Pantera Negra", Group.LIGA_DA_JUSTICA));
		
		listCodinomesLiga.add("Lanterna Verde");
		listCodinomesLiga.add("Flash");
		listCodinomesLiga.add("Aquaman");
		listCodinomesLiga.add("Batman");
		listCodinomesLiga.add("Superman");
		listCodinomesLiga.add("Mulher Maravilha");
		
		listCodinomesVingadores.add("Hulk");
		listCodinomesVingadores.add("Capitao America");
		listCodinomesVingadores.add("Pantera Negra");
		listCodinomesVingadores.add("Homem de Ferro");
		listCodinomesVingadores.add("Thor");
		listCodinomesVingadores.add("Feiticeira Escarlate");
		listCodinomesVingadores.add("Visao");
			
	}

}
