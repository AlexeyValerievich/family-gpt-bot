package petproject.telegrambot.openai;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Choices(
        @JsonProperty("message") Message message
) {}
