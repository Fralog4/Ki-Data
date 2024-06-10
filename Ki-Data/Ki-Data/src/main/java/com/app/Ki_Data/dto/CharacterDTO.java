package com.app.Ki_Data.dto;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CharacterDTO {
    private int id;
    private String name;
    private String race;
    private String gender;
    private int birthDate;
    private String description;
    private List<String> skillSet;
}
