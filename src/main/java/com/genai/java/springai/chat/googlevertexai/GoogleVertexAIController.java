package com.genai.java.springai.chat.googlevertexai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vertexai/chat")
@ConditionalOnProperty(prefix = "app.ai", name="provider", havingValue = "vertexai", matchIfMissing = true)
public class GoogleVertexAIController {

    private static final String SYSTEM_PROMPT = "You are a helpful assistant that generates professional JavaDoc comments for Java code." +
            "Always explain the purpose of the class or method, its parameters, return values, and any exceptions it may throw." +
            "Use the standard JavaDoc style with /** ... */." +
            "Keep explanations concise, clear, and technical.";

    private final ChatClient chatClient;

    public GoogleVertexAIController(@Qualifier("vertexAIChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("/generate-java-docs")
    public ChatClientResponse generateJavaDocs(@RequestBody String message) {
        return chatClient.prompt()
                .user(message)
                .system(SYSTEM_PROMPT)
                .call()
                .chatClientResponse();
    }
}
