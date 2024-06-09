package com.app.Ki_Data.service;

import com.app.Ki_Data.model.Character;
import com.app.Ki_Data.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService{
    @Autowired
    CharacterRepository characterRepository;

    public List<Character> getAllCharacter(){
        return characterRepository.findAll();
    }
    public Character getCharacterById(Long id){
        return characterRepository.findById(id).orElse(null);
    }
    public Character saveCharacter(Character character){
        return characterRepository.save(character);
    }
    public void deleteCharacterById(Long id){
        characterRepository.deleteById(id);
    }
}
