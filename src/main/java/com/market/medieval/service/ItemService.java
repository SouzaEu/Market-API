package com.market.medieval.service;

import com.market.medieval.dto.ItemDTO;
import com.market.medieval.dto.ItemResponseDTO;
import com.market.medieval.exception.ResourceNotFoundException;
import com.market.medieval.model.Character;
import com.market.medieval.model.Item;
import com.market.medieval.repository.CharacterRepository;
import com.market.medieval.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final CharacterRepository characterRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, CharacterRepository characterRepository) {
        this.itemRepository = itemRepository;
        this.characterRepository = characterRepository;
    }

    public Page<ItemResponseDTO> getAllItems(Pageable pageable) {
        Page<Item> itemPage = itemRepository.findAll(pageable);
        return itemPage.map(ItemResponseDTO::fromEntity);
    }

    public ItemResponseDTO getItemById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
        return ItemResponseDTO.fromEntity(item);
    }

    @Transactional
    public ItemResponseDTO createItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setType(itemDTO.getType());
        item.setRarity(itemDTO.getRarity());
        item.setPrice(itemDTO.getPrice());
        
        if (itemDTO.getOwnerId() != null) {
            Character owner = characterRepository.findById(itemDTO.getOwnerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Character", "id", itemDTO.getOwnerId()));
            item.setOwner(owner);
        }
        
        Item savedItem = itemRepository.save(item);
        return ItemResponseDTO.fromEntity(savedItem);
    }

    @Transactional
    public ItemResponseDTO updateItem(Long id, ItemDTO itemDTO) {
        Item item = itemRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
        
        item.setName(itemDTO.getName());
        item.setType(itemDTO.getType());
        item.setRarity(itemDTO.getRarity());
        item.setPrice(itemDTO.getPrice());
        
        if (itemDTO.getOwnerId() != null) {
            Character owner = characterRepository.findById(itemDTO.getOwnerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Character", "id", itemDTO.getOwnerId()));
            item.setOwner(owner);
        } else {
            item.setOwner(null);
        }
        
        Item updatedItem = itemRepository.save(item);
        return ItemResponseDTO.fromEntity(updatedItem);
    }

    @Transactional
    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Item", "id", id);
        }
        itemRepository.deleteById(id);
    }

    public Page<ItemResponseDTO> findItemsByName(String name, Pageable pageable) {
        Page<Item> itemPage = itemRepository.findByNameContainingIgnoreCase(name, pageable);
        return itemPage.map(ItemResponseDTO::fromEntity);
    }

    public Page<ItemResponseDTO> findItemsByType(Item.ItemType type, Pageable pageable) {
        Page<Item> itemPage = itemRepository.findByType(type, pageable);
        return itemPage.map(ItemResponseDTO::fromEntity);
    }

    public Page<ItemResponseDTO> findItemsByRarity(Item.ItemRarity rarity, Pageable pageable) {
        Page<Item> itemPage = itemRepository.findByRarity(rarity, pageable);
        return itemPage.map(ItemResponseDTO::fromEntity);
    }

    public Page<ItemResponseDTO> findItemsByPriceRange(int minPrice, int maxPrice, Pageable pageable) {
        Page<Item> itemPage = itemRepository.findByPriceBetween(minPrice, maxPrice, pageable);
        return itemPage.map(ItemResponseDTO::fromEntity);
    }

    public Page<ItemResponseDTO> findItemsByOwnerId(Long ownerId, Pageable pageable) {
        if (!characterRepository.existsById(ownerId)) {
            throw new ResourceNotFoundException("Character", "id", ownerId);
        }
        Page<Item> itemPage = itemRepository.findByOwnerId(ownerId, pageable);
        return itemPage.map(ItemResponseDTO::fromEntity);
    }
}
