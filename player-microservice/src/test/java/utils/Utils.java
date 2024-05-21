package utils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.Mockito;

import com.im2back.github.playermicroservice.model.group.Group;
import com.im2back.github.playermicroservice.model.player.Player;
import com.im2back.github.playermicroservice.model.player.dto.PlayerRegistrationRequestDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerRegistrationResponseDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerUpdateRequestDto;
import com.im2back.github.playermicroservice.model.player.dto.PlayerUpdateResponseDto;

public class Utils {

    public static final List<PlayerRegistrationResponseDto> listPlayerRegistrationResponseDto = createPlayerRegistrationResponseDtoList();
    public static final List<Player> listPlayer = createPlayerList();
    public static final List<String> listCodinomesVingadores = createCodinomesVingadoresList();
    public static final List<String> listCodinomesLiga = createCodinomesLigaList();

    public static final PlayerRegistrationRequestDto playerRegistrationRequestDto = new PlayerRegistrationRequestDto(
            "Tony Stark", "tony@stark.com", "11234567890", "Hulk", Group.VINGADORES);
    public static final PlayerRegistrationResponseDto playerRegistrationResponseDto = new PlayerRegistrationResponseDto(1L,
            "Tony Stark", "tony@stark.com", "11234567890", "Hulk", Group.VINGADORES);
    public static final PlayerUpdateRequestDto playerUpdateRequestDto = new PlayerUpdateRequestDto(1L, "Tony Boy",
            "tony@stark.com", "11234567890", "Hulk", Group.VINGADORES);
    public static final PlayerUpdateResponseDto playerUpdateResponseDto = new PlayerUpdateResponseDto(1L, "Tony Stark",
            "tony@stark.com", "11234567890", "Hulk", Group.VINGADORES);
    public static final Optional<Player> playerOptional = Optional.of(new Player(1L, "Tony Stark", "tony@stark.com", 
            "11234567890", "Hulk", Group.VINGADORES));
    public static final Player player = new Player(1L, "Tony Stark", "tony@stark.com", "11234567890", "Hulk", Group.VINGADORES);

    private static List<PlayerRegistrationResponseDto> createPlayerRegistrationResponseDtoList() {
        return Arrays.asList(
            new PlayerRegistrationResponseDto(1L, "Tony Stark", "tony@stark.com", "11234567890", "Hulk", Group.VINGADORES),
            new PlayerRegistrationResponseDto(2L, "Bruce Wayne", "bruce@prises.com", "10987654321", "Batman", Group.LIGA_DA_JUSTICA),
            new PlayerRegistrationResponseDto(3L, "Clark Kent", "clark@planet.com", "11122231333", "Pantera Negra", Group.LIGA_DA_JUSTICA)
        );
    }

    private static List<Player> createPlayerList() {
        return Arrays.asList(
            new Player(1L, "Tony Stark", "tony@stark.com", "11234567890", "Hulk", Group.VINGADORES),
            new Player(2L, "Bruce Wayne", "bruce@prises.com", "10987654321", "Batman", Group.LIGA_DA_JUSTICA),
            new Player(3L, "Clark Kent", "clark@planet.com", "11122231333", "Pantera Negra", Group.LIGA_DA_JUSTICA)
        );
    }

    private static List<String> createCodinomesVingadoresList() {
        return Arrays.asList("Hulk", "Capitao America", "Pantera Negra", "Homem de Ferro", "Thor", "Feiticeira Escarlate", "Visao");
    }

    private static List<String> createCodinomesLigaList() {
        return Arrays.asList("Lanterna Verde", "Flash", "Aquaman", "Batman", "Superman", "Mulher Maravilha");
    }
    
    public static Player mapDtoToPlayer(PlayerRegistrationRequestDto dto) {
        return new Player(dto.getName(), dto.getEmail(), dto.getPhone(), dto.getAlias(), dto.getGroup());
    }

    public static PlayerRegistrationResponseDto mapPlayerToDto(Player player) {
        return new PlayerRegistrationResponseDto(player.getId(), player.getName(), player.getEmail(), 
                                                 player.getPhone(), player.getAlias(), player.getGroup());
    }
    
    public static PlayerUpdateResponseDto mapPlayerToDtoUpdate(Player player) {
        return new PlayerUpdateResponseDto(player.getId(), player.getName(), player.getEmail(), 
                                                 player.getPhone(), player.getAlias(), player.getGroup());
    }
    
    public static Player createMockPlayer() {
        Player mockPlayer = Mockito.mock(Player.class);
        Mockito.when(mockPlayer.getId()).thenReturn(1L);
        Mockito.when(mockPlayer.getName()).thenReturn("Tony Stark");
        Mockito.when(mockPlayer.getEmail()).thenReturn("tony@stark.com");
        Mockito.when(mockPlayer.getPhone()).thenReturn("11234567890");
        Mockito.when(mockPlayer.getAlias()).thenReturn("Hulk");
        Mockito.when(mockPlayer.getGroup()).thenReturn(Group.VINGADORES);
        return mockPlayer;
    }

}
