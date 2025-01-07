package petproject.telegrambot.openai;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

public record ChatCompletionObject (
        @JsonProperty("choices") List<Choices> choices
){}
