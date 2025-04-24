package com.market.medieval.controller;

import com.market.medieval.dto.ItemDTO;
import com.market.medieval.dto.ItemResponseDTO;
import com.market.medieval.model.Item;
import com.market.medieval.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<Page<ItemResponseDTO>> getAllItems(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(itemService.getAllItems(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> createItem(@Valid @RequestBody ItemDTO itemDTO) {
        return new ResponseEntity<>(itemService.createItem(itemDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> updateItem(
            @PathVariable Long id, 
            @Valid @RequestBody ItemDTO itemDTO) {
        return ResponseEntity.ok(itemService.updateItem(id, itemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/name")
    public ResponseEntity<Page<ItemResponseDTO>> searchItemsByName(
            @RequestParam String name,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(itemService.findItemsByName(name, pageable));
    }

    @GetMapping("/search/type")
    public ResponseEntity<Page<ItemResponseDTO>> searchItemsByType(
            @RequestParam Item.ItemType type,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(itemService.findItemsByType(type, pageable));
    }

    @GetMapping("/search/rarity")
    public ResponseEntity<Page<ItemResponseDTO>> searchItemsByRarity(
            @RequestParam Item.ItemRarity rarity,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(itemService.findItemsByRarity(rarity, pageable));
    }

    @GetMapping("/search/price")
    public ResponseEntity<Page<ItemResponseDTO>> searchItemsByPriceRange(
            @RequestParam(defaultValue = "0") int minPrice,
            @RequestParam(defaultValue = "999999") int maxPrice,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(itemService.findItemsByPriceRange(minPrice, maxPrice, pageable));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<Page<ItemResponseDTO>> getItemsByOwnerId(
            @PathVariable Long ownerId,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(itemService.findItemsByOwnerId(ownerId, pageable));
    }
}
