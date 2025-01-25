package com.app.Ki_Data.service;

import com.app.Ki_Data.dto.CharacterMapper;
import com.app.Ki_Data.dto.CharacterPgDTO;
import com.app.Ki_Data.model.CharacterPg;
import com.app.Ki_Data.repository.CharacterRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CharacterService{

    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<CharacterPgDTO> getAllCharacter(){
        return characterRepository.findAll()
                .stream()
                .map(CharacterMapper::toDTO)
                .collect(Collectors.toList());
    }
    public CharacterPgDTO getCharacterById(int id){
        return characterRepository.findById(id)
                .map(CharacterMapper::toDTO)
                .orElseGet(() -> {
                    log.error("Character with id " + id + " not found");
                    return null;
                });
    }
    public CharacterPgDTO saveCharacter(CharacterPgDTO characterDTO){
        try {
            CharacterPg characterPg = CharacterMapper.toEntity(characterDTO);
            log.debug("The character is : " + characterPg);
            assert characterPg != null;
            return CharacterMapper.toDTO(characterRepository.save(characterPg));
        } catch (Exception e) {
            log.error("Error saving character: " + e.getMessage());
            throw new RuntimeException("Error saving character: " + e.getMessage());
        }
    }
    public void deleteCharacterById(int id){
        characterRepository.deleteById(id);
        log.info("The Character with id "+id+" has been deleted");
    }
}
