package com.app.Ki_Data.controller

import com.app.Ki_Data.dto.CharacterPgDTO
import com.app.Ki_Data.service.CharacterService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kiData")
@Tag(name = "Characters", description = "Endpoints for managing characters")
@SecurityScheme(
    name = "JWT",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT",
    `in` = SecuritySchemeIn.HEADER,
    description = "JWT token for authentication"
)
class CharacterController (private val service : CharacterService) {

    @Operation(
        summary = "Get all characters",
        description = "Returns a list of all characters",
        responses = [ApiResponse(
            responseCode = "200",
            description = "Characters retrieved successfully",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = CharacterPgDTO::class))]
        ), ApiResponse(responseCode = "400", description = "Invalid request"), ApiResponse(
            responseCode = "404",
            description = "Characters not found"
        )]
    )
    @GetMapping("/characters")
    fun getAllCharacters() : List<CharacterPgDTO>{
        return service.getAllCharacters()
    }


    @Operation(
        summary = "Get character by an id",
        responses = [ApiResponse(
            responseCode = "200",
            description = "Character retrieved successfully",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = CharacterPgDTO::class))]
        ), ApiResponse(responseCode = "400", description = "Invalid request"), ApiResponse(
            responseCode = "404",
            description = "Character not found"
        )]
    )
    @Parameter(name = "id", description = "The id of the character to retrieve", required = true)

    @GetMapping("/characters/{id}")
    fun getCharacterById(@PathVariable id : Int) : CharacterPgDTO{
        return service.getCharacterById(id)
    }

    @Operation(
        summary = "Create a new character",
        description = "Only ADMIN can create a new character",
        responses = [ApiResponse(
            responseCode = "200",
            description = "Character created successfully",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = CharacterPgDTO::class))]
        ), ApiResponse(responseCode = "400", description = "Invalid request"), ApiResponse(
            responseCode = "403",
            description = "Unauthorized request"
        )],
        security = [SecurityRequirement(name = "JWT")]
    )
    @PreAuthorize("hasAnyRole('ADMIN')") //enum?
    @PostMapping("/characters")
    fun saveCharacter(@RequestBody character : CharacterPgDTO) : CharacterPgDTO{
        return service.saveCharacter(character)
    }

    @Operation(
        summary = "Delete a character by an id",
        description = "Only ADMIN can delete a character",
        responses = [ApiResponse(
            responseCode = "200",
            description = "Character deleted successfully",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = CharacterPgDTO::class))]
        ), ApiResponse(responseCode = "400", description = "Invalid request"), ApiResponse(
            responseCode = "403",
            description = "Unauthorized request"
        )],
        security = [SecurityRequirement(name = "JWT")]
    )
    @Parameter(name = "id", description = "The id of the character to delete", required = true)
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping
    fun deleteCharacterById(@PathVariable id:Int){
        return service.deleteCharacterById(id)
    }
}