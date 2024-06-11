package com.app.Ki_Data.dto;

import org.modelmapper.ModelMapper;
public class CharacterMapper {
    private static final ModelMapper modelMapper=new ModelMapper();
    public static com.app.Ki_Data.model.CharacterPg toEntity(CharacterPgDTO dto) {
        return modelMapper.map(dto, com.app.Ki_Data.model.CharacterPg.class);
    }

    public static CharacterPgDTO toDTO(com.app.Ki_Data.model.CharacterPg characterPg) {
        return modelMapper.map(characterPg, CharacterPgDTO.class);
    }
}
