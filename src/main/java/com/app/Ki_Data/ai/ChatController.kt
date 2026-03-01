package com.app.Ki_Data.ai

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.ai.chat.messages.SystemMessage
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kiData")
@Tag(name = "AI", description = "AI Chat endpoints")
class ChatController (
    private val chatModel: ChatModel,
    @Value("\${spring.ai.prompt}") val sysPrompt : String
){
    @Operation(
        summary = "Generate a chat with Mistral AI",
        description = "Generate a chat with Mistral AI",
        tags = ["AI"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "Chat generated successfully",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = MutableMap::class))]
        ), ApiResponse(responseCode = "400", description = "Invalid request")]
    )

    @GetMapping("/ai/chat")
    fun generate(@RequestParam(value = "message", defaultValue = "Who is Jack?") message: String) = mapOf("generation" to chatModel.call(message))

    @Operation(
        summary = "Generate a chat with the Namekian Dende",
        description = "Generate a chat with the Namekian named Dende",
        tags = ["AI"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "Chat generated successfully",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = String::class))]
        ), ApiResponse(responseCode = "400", description = "Invalid request")]
    )
    @GetMapping("/ai/dendeChat")
    fun generateWithPrompt(@RequestParam(value = "message") message: String) : String{

        val messages = listOf(
            SystemMessage(sysPrompt),
            UserMessage(message)
        )
        val prompt= Prompt(message)
        return try {
            chatModel.call(prompt).result.output.content
        } catch (e: Exception) {
            throw RuntimeException("Error during communication : \${e.message}", e)
        }

    }
}