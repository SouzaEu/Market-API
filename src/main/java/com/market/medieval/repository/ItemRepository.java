package com.market.medieval.repository;

import com.market.medieval.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    
    Page<Item> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
    Page<Item> findByType(Item.ItemType type, Pageable pageable);
    
    Page<Item> findByRarity(Item.ItemRarity rarity, Pageable pageable);
    
    @Query("SELECT i FROM Item i WHERE i.price >= :minPrice AND i.price <= :maxPrice")
    Page<Item> findByPriceBetween(@Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice, Pageable pageable);
    
    Page<Item> findByOwnerId(Long ownerId, Pageable pageable);
}
