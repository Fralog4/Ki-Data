package com.app.Ki_Data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@NoArgsConstructor
@Table(name="Goku")
public class Goku extends Character {
    public Goku(int id, String name, String race, String gender, int birthDate, String description, List<String> skillset) {
        super();
        this.setId(id);
        this.setName(name);
        this.setRace(race);
        this.setGender(gender);
        this.setBirthDate(birthDate);
        this.setDescription(description);
        this.setSkillSet(skillset);
    }
}