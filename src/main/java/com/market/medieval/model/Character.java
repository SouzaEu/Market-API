package com.market.medieval.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Class is required")
    @Enumerated(EnumType.STRING)
    private CharacterClass characterClass;

    @Min(value = 1, message = "Level must be at least 1")
    @Max(value = 99, message = "Level cannot exceed 99")
    private int level = 1;

    @Min(value = 0, message = "Coins cannot be negative")
    private int coins = 0;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    public enum CharacterClass {
        WARRIOR, MAGE, ARCHER
    }
}
