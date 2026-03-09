package com.app.Ki_Data.dto

import com.app.Ki_Data.model.CharacterPg

// 1. Da Entity a DTO
fun CharacterPg.toDTO() = CharacterPgDTO(
    id = this.id,
    name =this.name,
    race =this.race,
    gender =this.gender,
    birthDate =this.birthDate,
    description =this.description,
    skillSet =this.skillSet
)

// 2. Da DTO a Entity
fun CharacterPgDTO.toEntity() = CharacterPg(
    id = this.id,
    name = this.name,
    race = this.race,
    gender = this.gender,
    birthDate = this.birthDate,
    description = this.description,
    skillSet = this.skillSet
)