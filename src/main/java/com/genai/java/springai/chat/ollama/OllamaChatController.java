package com.genai.java.springai.chat.ollama;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ollama/chat")
@ConditionalOnProperty(prefix = "app.ai", name="provider", havingValue = "ollmaai")
public class OllamaChatController {

    private static final String SYSTEM_PROMPT = "You are a helpful assistant that drafts professional and concise emails based on user input." +
            "Ensure the emails are clear, polite, and tailored to the specified context." +
            "Use a formal and respectful tone while maintaining brevity." +
            "if any other context provided say : 'I can only Help on creating e-mail Task'";

    private final ChatClient chatClient;

    public OllamaChatController(@Qualifier("ollamaAIChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("/draft-email")
    public String draftEmail(@RequestBody String message) {
        System.out.println("DRAFT-EMAIL");
        return chatClient.prompt()
              .system(SYSTEM_PROMPT)
                .user(message)
                .call().content();

    }
}
