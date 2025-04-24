package com.market.medieval.dto;

import com.market.medieval.model.Character;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterResponseDTO {

    private Long id;
    private String name;
    private Character.CharacterClass characterClass;
    private int level;
    private int coins;
    private List<Long> itemIds; // Apenas IDs dos itens para evitar muitos dados

    // Método auxiliar para converter Entidade para DTO (pode ser movido para um Mapper depois)
    public static CharacterResponseDTO fromEntity(Character character) {
        if (character == null) {
            return null;
        }
        List<Long> itemIds = character.getItems() != null ? 
                             character.getItems().stream().map(item -> item.getId()).toList() : 
                             List.of();
        return new CharacterResponseDTO(
                character.getId(),
                character.getName(),
                character.getCharacterClass(),
                character.getLevel(),
                character.getCoins(),
                itemIds
        );
    }
} 