package com.market.medieval.controller;

import com.market.medieval.dto.CharacterDTO;
import com.market.medieval.dto.CharacterResponseDTO;
import com.market.medieval.model.Character;
import com.market.medieval.service.CharacterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public ResponseEntity<Page<CharacterResponseDTO>> getAllCharacters(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(characterService.getAllCharacters(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterResponseDTO> getCharacterById(@PathVariable Long id) {
        return ResponseEntity.ok(characterService.getCharacterById(id));
    }

    @PostMapping
    public ResponseEntity<CharacterResponseDTO> createCharacter(@Valid @RequestBody CharacterDTO characterDTO) {
        return new ResponseEntity<>(characterService.createCharacter(characterDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterResponseDTO> updateCharacter(
            @PathVariable Long id, 
            @Valid @RequestBody CharacterDTO characterDTO) {
        return ResponseEntity.ok(characterService.updateCharacter(id, characterDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/name")
    public ResponseEntity<Page<CharacterResponseDTO>> searchCharactersByName(
            @RequestParam String name,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(characterService.findCharactersByName(name, pageable));
    }

    @GetMapping("/search/class")
    public ResponseEntity<Page<CharacterResponseDTO>> searchCharactersByClass(
            @RequestParam Character.CharacterClass characterClass,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(characterService.findCharactersByClass(characterClass, pageable));
    }
}
