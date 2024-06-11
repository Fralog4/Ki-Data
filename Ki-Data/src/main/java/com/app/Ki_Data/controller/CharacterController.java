package com.app.Ki_Data.controller;

import com.app.Ki_Data.model.Character;
import com.app.Ki_Data.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    @Autowired
    CharacterService characterService;

    @GetMapping
    public List<Character> getAllCharacters(){
        return characterService.getAllCharacter();
    }

    @GetMapping("/{id}")
    public Character getCharacterById(@PathVariable Long id){
        return characterService.getCharacterById(id);
    }

    @Secured("ADMIN")
    @PostMapping("/create")
    public Character saveCharacter(@RequestBody Character character){
        return characterService.saveCharacter(character);
    }
    @Secured("ADMIN")
    @DeleteMapping("/delete/{id}")
    public void deleteCharacterById(@RequestBody Long id){
        characterService.deleteCharacterById(id);
    }
}
