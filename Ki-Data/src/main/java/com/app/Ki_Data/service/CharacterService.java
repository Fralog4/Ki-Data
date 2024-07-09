package com.app.Ki_Data.service;

import com.app.Ki_Data.dto.CharacterPgDTO;
import com.app.Ki_Data.dto.CharacterMapper;
import com.app.Ki_Data.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacterService{
    @Autowired
    private CharacterRepository characterRepository;

    public List<CharacterPgDTO> getAllCharacter(){
        return characterRepository.findAll()
                .stream()
                .map(CharacterMapper::toDTO)
                .collect(Collectors.toList());
    }
    public CharacterPgDTO getCharacterById(int id){
        return characterRepository.findById(id)
                .map(CharacterMapper::toDTO)
                .orElse(null);
    }
    public CharacterPgDTO saveCharacter(CharacterPgDTO characterDTO){
        com.app.Ki_Data.model.CharacterPg characterPg = CharacterMapper.toEntity(characterDTO);
        assert characterPg != null;
        return CharacterMapper.toDTO(characterRepository.save(characterPg));

    }
    public void deleteCharacterById(int id){
        characterRepository.deleteById(id);
    }
}
