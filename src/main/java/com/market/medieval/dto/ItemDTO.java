package com.market.medieval.dto;

import com.market.medieval.model.Item;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Type is required")
    private Item.ItemType type;

    @NotNull(message = "Rarity is required")
    private Item.ItemRarity rarity;

    @Min(value = 0, message = "Price cannot be negative")
    private int price;

    private Long ownerId;
}
