package petproject.telegrambot.api;

import lombok.Builder;
import java.util.List;

@Builder
public record ChatCompletionRequest(
        String model,
        List<Message> messages
) {}