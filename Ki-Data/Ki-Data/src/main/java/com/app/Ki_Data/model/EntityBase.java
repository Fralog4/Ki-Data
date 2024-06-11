package com.app.Ki_Data.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@MappedSuperclass
@DiscriminatorColumn(name = "character_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public abstract class EntityBase {
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
