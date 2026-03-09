package com.app.Ki_Data.model

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity

@Entity
class CharacterPg(
    id : Int=0,
    name: String? = null,
    race: String? = null,
    gender: String? = null,
    birthDate: Int = 0,
    description: String? = null,

    @ElementCollection
    var skillSet: MutableList<String>? = null
): EntityBase(id,name,race,gender,birthDate,description)