package com.app.Ki_Data.dto

data class CharacterPgDTO(
    val id: Int = 0,
    val name: String? = null,
    val race: String? = null,
    val gender: String? = null,
    val birthDate: Int = 0,
    val description: String? = null,
    val skillSet: MutableList<String>? = null
)