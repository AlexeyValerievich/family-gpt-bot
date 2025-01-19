package petproject.telegrambot.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Choices(
        @JsonProperty("message") Message message
) {}
