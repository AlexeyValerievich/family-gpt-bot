package petproject.telegrambot.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
public record Message (
        @JsonProperty("role") String role,
        @JsonProperty("content") String content
) {}
