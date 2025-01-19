package petproject.telegrambot.api;

import lombok.Builder;

import java.util.List;

@Builder
public record ChatHistory(
        List<Message> chatMessage
) {
}
