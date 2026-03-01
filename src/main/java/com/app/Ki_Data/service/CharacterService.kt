package com.app.Ki_Data.service

import com.app.Ki_Data.dto.CharacterPgDTO
import com.app.Ki_Data.dto.toDTO
import com.app.Ki_Data.dto.toEntity
import com.app.Ki_Data.model.CharacterPg
import com.app.Ki_Data.repository.CharacterRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CharacterService (private val characterRepository : CharacterRepository){

    fun getAllCharacters(): List<CharacterPgDTO> {
        return characterRepository.findAll().stream().map { it.toDTO() }.toList()
    }

    fun getCharacterById(id : Int): CharacterPgDTO{
        return characterRepository.findByIdOrNull(id)?.toDTO() ?: throw RuntimeException("No Character available for this id : $id")
    }

    fun saveCharacter(characterPgDTO: CharacterPgDTO): CharacterPgDTO{
        return try{
            val entity = characterPgDTO.toEntity()
            characterRepository.save(entity).toDTO()
        } catch (e : Exception){
            throw RuntimeException("Error saving character: ${e.message}", e)        }
    }

    fun deleteCharacterById(id:Int){
        return characterRepository.deleteById(id)
    }
}