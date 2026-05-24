package com.genai.java.springai.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AiProviderConfig {


    @Bean("openAIChatClient")
    @ConditionalOnProperty(prefix = "app.ai", name="provider", havingValue = "openai")
    ChatClient openAIChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel).build();
    }


    @Bean("vertexAIChatClient")
    @ConditionalOnProperty(prefix = "app.ai", name="provider", havingValue = "vertexai", matchIfMissing = true)
    ChatClient vertexAIChatClient(VertexAiGeminiChatModel vertexAiGeminiChatModel) {
        return ChatClient.builder(vertexAiGeminiChatModel).build();
    }

    @Bean("ollamaAIChatClient")
    @ConditionalOnProperty(prefix = "app.ai", name="provider", havingValue = "ollmaai")
    ChatClient ollamaAIChatClient(OllamaChatModel ollamaChatModel) {
        return ChatClient.builder(ollamaChatModel).build();
    }


    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> builder.modules(new JavaTimeModule());
    }
}
