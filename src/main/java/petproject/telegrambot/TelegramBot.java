package petproject.telegrambot;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import petproject.telegrambot.openai.OpenAIClient;

public class TelegramBot extends TelegramLongPollingBot {

    private final OpenAIClient openAIClient;

    public TelegramBot(DefaultBotOptions options, String botToken, OpenAIClient openAIClient) {
        super(options, botToken);
        this.openAIClient = openAIClient;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var text = update.getMessage().getText();
            var chatId = update.getMessage().getChatId();

            var chatCompletionResponse = openAIClient.createChatCompletion(text);

            var textResponse = chatCompletionResponse.choices().getFirst().message().content();

            SendMessage sendMessage = new SendMessage(chatId.toString(), textResponse);
            try {
                sendApiMethod(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public String getBotUsername() {
        return "My-test-bot";
    }
}
