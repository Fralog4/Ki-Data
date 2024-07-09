package com.app.Ki_Data.controller;

import com.app.Ki_Data.dto.CharacterPgDTO;
import com.app.Ki_Data.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    @Autowired
    CharacterService characterService;

    @GetMapping
    public List<CharacterPgDTO> getAllCharacters(){
        return characterService.getAllCharacter();
    }

    @GetMapping("/{id}")
    public CharacterPgDTO getCharacterById(@PathVariable int id){
        return characterService.getCharacterById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/create")
    public CharacterPgDTO saveCharacter(@RequestBody CharacterPgDTO characterPgDTO) {
        return characterService.saveCharacter(characterPgDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteCharacterById(@PathVariable int id){
        characterService.deleteCharacterById(id);
    }
}
