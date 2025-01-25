package com.app.Ki_Data.ai;

import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/kiData")
public class ChatController {

    private final MistralAiChatModel chatModel;

    @Value("${spring.ai.prompt}")
    private String sysPrompt;

    @Autowired
    public ChatController(MistralAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/ai/generate")
    public Map<String,String> generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return Map.of("generation", this.chatModel.call(message));
    }


    @GetMapping("/ai/dende")
    public String generateWithPrompt(@RequestParam(value = "message", defaultValue = "You are a Namekian named Dende") String message) {

        SystemMessage systemMessage = new SystemMessage(sysPrompt);
        Prompt prompt= new Prompt(
                new SystemMessage(systemMessage.getContent()),
                new UserMessage(message)
        );

        return this.chatModel.call(prompt).getResult().getOutput().getContent();
    }
}