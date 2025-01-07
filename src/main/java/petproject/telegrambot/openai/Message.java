package petproject.telegrambot.openai;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

public record Message (
        @JsonProperty("role") String role,
        @JsonProperty("content") String content
) {}
