package com.app.Ki_Data.controller;

import com.app.Ki_Data.dto.CharacterDTO;
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
    public List<CharacterDTO> getAllCharacters(){
        return characterService.getAllCharacter();
    }

    @GetMapping("/{id}")
    public CharacterDTO getCharacterById(@PathVariable int id){
        return characterService.getCharacterById(id);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/create")
    public CharacterDTO saveCharacter(@RequestBody CharacterDTO characterDTO) {
        System.out.println("Received CharacterDTO: " + characterDTO);
        return characterService.saveCharacter(characterDTO);
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete/{id}")
    public void deleteCharacterById(@PathVariable int id){
        characterService.deleteCharacterById(id);
    }
}
