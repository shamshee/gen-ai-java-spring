package com.genai.java.springai.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiProviderConfig {


    @Bean("openAIChatClient")
    @ConditionalOnProperty(prefix = "app.ai", name="provider", havingValue = "openai")
    ChatClient openAIChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel).build();
    }

    @Bean
    ObjectMapper objectmapper(){
        return  new ObjectMapper();
    }

    @Bean("vertexAIChatClient")
    @ConditionalOnProperty(prefix = "app.ai", name="provider", havingValue = "vertexai", matchIfMissing = true)
    ChatClient vertexAIChatClient(VertexAiGeminiChatModel vertexAiGeminiChatModel) {
        return ChatClient.builder(vertexAiGeminiChatModel).build();
    }
}
