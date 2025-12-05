package com.codegnan.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.content.Media;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

@Service
public class DescriptionService {

    private final ChatClient chatClient;

    public DescriptionService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String describe(byte[] imageBytes, String mimeTypeStr) {
        String prompt = """
                Describe the image in detail using bullet points and short paragraphs.
                Include objects, people, colors, text (if any), actions, mood, background, and any important context.
                """;

        MimeType mimeType = MimeTypeUtils.parseMimeType(mimeTypeStr);
        Media media = new Media(mimeType, new ByteArrayResource(imageBytes));

        return chatClient.prompt()
                .system("You are an expert image analyst and describer.")
                .user(u -> u.text(prompt).media(media))   // this line sends the image
                .call()
                .content();
    }
}