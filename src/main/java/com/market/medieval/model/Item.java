package com.market.medieval.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Type is required")
    @Enumerated(EnumType.STRING)
    private ItemType type;

    @NotNull(message = "Rarity is required")
    @Enumerated(EnumType.STRING)
    private ItemRarity rarity;

    @Min(value = 0, message = "Price cannot be negative")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonIgnoreProperties({"items", "hibernateLazyInitializer", "handler"})
    private Character owner;

    public enum ItemType {
        WEAPON, ARMOR, POTION, ACCESSORY
    }

    public enum ItemRarity {
        COMMON, RARE, EPIC, LEGENDARY
    }
}
