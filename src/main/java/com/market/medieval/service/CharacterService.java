package com.market.medieval.service;

import com.market.medieval.dto.CharacterDTO;
import com.market.medieval.dto.CharacterResponseDTO;
import com.market.medieval.exception.ResourceNotFoundException;
import com.market.medieval.model.Character;
import com.market.medieval.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public Page<CharacterResponseDTO> getAllCharacters(Pageable pageable) {
        Page<Character> characterPage = characterRepository.findAll(pageable);
        return characterPage.map(CharacterResponseDTO::fromEntity);
    }

    public CharacterResponseDTO getCharacterById(Long id) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Character", "id", id));
        return CharacterResponseDTO.fromEntity(character);
    }

    @Transactional
    public CharacterResponseDTO createCharacter(CharacterDTO characterDTO) {
        Character character = new Character();
        character.setName(characterDTO.getName());
        character.setCharacterClass(characterDTO.getCharacterClass());
        character.setLevel(characterDTO.getLevel());
        character.setCoins(characterDTO.getCoins());
        
        Character savedCharacter = characterRepository.save(character);
        return CharacterResponseDTO.fromEntity(savedCharacter);
    }

    @Transactional
    public CharacterResponseDTO updateCharacter(Long id, CharacterDTO characterDTO) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Character", "id", id));
        
        character.setName(characterDTO.getName());
        character.setCharacterClass(characterDTO.getCharacterClass());
        character.setLevel(characterDTO.getLevel());
        character.setCoins(characterDTO.getCoins());
        
        Character updatedCharacter = characterRepository.save(character);
        return CharacterResponseDTO.fromEntity(updatedCharacter);
    }

    @Transactional
    public void deleteCharacter(Long id) {
        if (!characterRepository.existsById(id)) {
             throw new ResourceNotFoundException("Character", "id", id);
        }
        characterRepository.deleteById(id);
    }

    public Page<CharacterResponseDTO> findCharactersByName(String name, Pageable pageable) {
        Page<Character> characterPage = characterRepository.findByNameContainingIgnoreCase(name, pageable);
        return characterPage.map(CharacterResponseDTO::fromEntity);
    }

    public Page<CharacterResponseDTO> findCharactersByClass(Character.CharacterClass characterClass, Pageable pageable) {
        Page<Character> characterPage = characterRepository.findByCharacterClass(characterClass, pageable);
         return characterPage.map(CharacterResponseDTO::fromEntity);
    }
}
