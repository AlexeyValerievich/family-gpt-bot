package petproject.telegrambot.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ChatComplectionResponse(
        @JsonProperty("choices") List<Choices> choices
){}
