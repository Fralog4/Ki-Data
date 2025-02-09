package com.app.Ki_Data.controller;

import com.app.Ki_Data.dto.CharacterPgDTO;
import com.app.Ki_Data.service.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kiData")
@Tag(name = "Characters", description = "Endpoints for managing characters")
@SecurityScheme(name = "JWT", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT", in = SecuritySchemeIn.HEADER, description = "JWT token for authentication")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @Operation(summary = "Get all characters", description = "Returns a list of all characters",
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Characters retrieved successfully",
                content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CharacterPgDTO.class))),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Characters not found")
            })
    @GetMapping("/characters")
    public List<CharacterPgDTO> getAllCharacters(){
        return characterService.getAllCharacter();
    }

    @Operation(summary = "Get character by an id",responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Character retrieved successfully",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CharacterPgDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Character not found")
    })
    @Parameter(name = "id", description = "The id of the character to retrieve", required = true)
    @GetMapping("/characters/{id}")
    public CharacterPgDTO getCharacterById(@PathVariable int id){
        return characterService.getCharacterById(id);
    }

    @Operation(summary = "Create a new character", description = "Only ADMIN can create a new character",
    responses = {

            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Character created successfully",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CharacterPgDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Unauthorized request"),

    },
    security = {
            @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "JWT")
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/characters")
    public CharacterPgDTO saveCharacter(@RequestBody CharacterPgDTO characterPgDTO) {
        return characterService.saveCharacter(characterPgDTO);
    }

    @Operation(summary = "Delete a character by an id", description = "Only ADMIN can delete a character",
            responses = {

                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Character deleted successfully",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CharacterPgDTO.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Unauthorized request")

            },
    security = {
            @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "JWT")
    })
    @Parameter(name = "id", description = "The id of the character to delete", required = true)
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/characters/{id}")
    public void deleteCharacterById(@PathVariable int id){
        characterService.deleteCharacterById(id);
    }
}
