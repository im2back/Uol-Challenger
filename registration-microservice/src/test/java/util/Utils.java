package util;



	import java.util.Arrays;
import java.util.List;

import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationRequestDto;
import com.im2back.github.registrationmicroservice.model.dto.PlayerRegistrationResponseDto;


	public class Utils {

	    public static final List<String> listCodinomesVingadores = createCodinomesVingadoresList();
	    public static final List<String> listCodinomesLiga = createCodinomesLigaList();

	    public static final PlayerRegistrationRequestDto playerRegistrationRequestDto = new PlayerRegistrationRequestDto(
	            "Tony Stark", "tony@stark.com", "11234567890", "Hulk", "VINGADORES");
	    public static final PlayerRegistrationResponseDto playerRegistrationResponseDto = new PlayerRegistrationResponseDto(1L,
	            "Tony Stark", "tony@stark.com", "11234567890", "Hulk", "VINGADORES");

	    private static List<String> createCodinomesVingadoresList() {
	        return Arrays.asList("Hulk", "Capitao America", "Pantera Negra", "Homem de Ferro", "Thor", "Feiticeira Escarlate", "Visao");
	    }

	    private static List<String> createCodinomesLigaList() {
	        return Arrays.asList("Lanterna Verde", "Flash", "Aquaman", "Batman", "Superman", "Mulher Maravilha");
	    }	    	

	}



