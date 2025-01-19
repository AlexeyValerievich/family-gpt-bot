package petproject.telegrambot.openai;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import petproject.telegrambot.api.ChatCompletionRequest;
import petproject.telegrambot.api.ChatGptHistoryService;
import petproject.telegrambot.api.Message;
import petproject.telegrambot.api.OpenAIClient;

@Service
@AllArgsConstructor
public class ChatGptService {
    private final OpenAIClient openAIClient;
    private final ChatGptHistoryService chatGptHistoryService;

    @Nonnull
    public String getResponseChatForUser(Long userId, String userTextInput) {

        chatGptHistoryService.createHistoryIfNotExist(userId);

        var history = chatGptHistoryService.addMessageToHistory(
                userId,
                Message.builder()
                        .content(userTextInput)
                        .role("user")
                        .build()
        );

        var request = ChatCompletionRequest.builder()
                .model("gpt-4")
                .messages(history.chatMessage())
                .build();
        var response = openAIClient.createChatCompletion(request);

        var messageFromGpt = response.choices().get(0).message();
        chatGptHistoryService.addMessageToHistory(userId, messageFromGpt);
        return messageFromGpt.toString();
    }
}
