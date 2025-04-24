package com.market.medieval.dto;

import com.market.medieval.model.Character;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Class is required")
    private Character.CharacterClass characterClass;

    @Min(value = 1, message = "Level must be at least 1")
    @Max(value = 99, message = "Level cannot exceed 99")
    private int level = 1;

    @Min(value = 0, message = "Coins cannot be negative")
    private int coins = 0;
}
