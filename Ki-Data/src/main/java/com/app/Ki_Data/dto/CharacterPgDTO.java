package com.app.Ki_Data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CharacterPgDTO {
    private int id;
    private String name;
    private String race;
    private String gender;
    private int birthDate;
    private String description;
    private List<String> skillSet;
}
