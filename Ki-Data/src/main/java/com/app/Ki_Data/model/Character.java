package com.app.Ki_Data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "character_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String race;
    private String gender;
    private int birthDate;
    private String description;

    @ElementCollection
    private List<String> skillSet;
}
