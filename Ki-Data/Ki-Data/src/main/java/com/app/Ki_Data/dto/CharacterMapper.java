package com.app.Ki_Data.dto;

import com.app.Ki_Data.model.Character;
import org.modelmapper.ModelMapper;
public class CharacterMapper {
    private static final ModelMapper modelMapper=new ModelMapper();
    public static Character toEntity(CharacterDTO dto) {
        return modelMapper.map(dto,Character.class);
    }

    public static CharacterDTO toDTO(Character character) {
        return modelMapper.map(character, CharacterDTO.class);
    }
}
