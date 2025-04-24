package com.market.medieval.dto;

import com.market.medieval.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDTO {

    private Long id;
    private String name;
    private Item.ItemType type;
    private Item.ItemRarity rarity;
    private int price;
    private Long ownerId;
    private String ownerName; // Adicionando nome do dono

    // Método auxiliar para converter Entidade para DTO
    public static ItemResponseDTO fromEntity(Item item) {
        if (item == null) {
            return null;
        }
        Long ownerId = (item.getOwner() != null) ? item.getOwner().getId() : null;
        String ownerName = (item.getOwner() != null) ? item.getOwner().getName() : null;

        return new ItemResponseDTO(
                item.getId(),
                item.getName(),
                item.getType(),
                item.getRarity(),
                item.getPrice(),
                ownerId,
                ownerName
        );
    }
} 