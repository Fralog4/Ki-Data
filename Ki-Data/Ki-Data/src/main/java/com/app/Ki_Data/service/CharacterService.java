package com.app.Ki_Data.service;

import com.app.Ki_Data.dto.CharacterDTO;
import com.app.Ki_Data.dto.CharacterMapper;
import com.app.Ki_Data.model.Character;
import com.app.Ki_Data.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacterService{
    @Autowired
    CharacterRepository characterRepository;

    public List<CharacterDTO> getAllCharacter(){
        return characterRepository.findAll()
                .stream()
                .map(CharacterMapper::toDTO)
                .collect(Collectors.toList());
    }
    public CharacterDTO getCharacterById(int id){
        return characterRepository.findById(id)
                .map(CharacterMapper::toDTO)
                .orElse(null);
    }
    public CharacterDTO saveCharacter(CharacterDTO characterDTO){
        Character character= CharacterMapper.toEntity(characterDTO);
        assert character != null;
        return CharacterMapper.toDTO(characterRepository.save(character));

    }
    public void deleteCharacterById(int id){
        characterRepository.deleteById(id);
    }
}
