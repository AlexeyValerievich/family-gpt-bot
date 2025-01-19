package petproject.telegrambot.command.handler;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import petproject.telegrambot.api.ChatGptHistoryService;
import petproject.telegrambot.command.TelegramCommandHandler;
import petproject.telegrambot.command.TelegramCommands;

@Component
@AllArgsConstructor
public class ClearChatHistoryCommandHandler implements TelegramCommandHandler {

    private final ChatGptHistoryService chatGptHistoryService;
    private final String CLEAR_HISTORY_MESSAGE = "Ваша история была очищенна";

    @Override
    public BotApiMethod<?> processCommand(Update update) {
        chatGptHistoryService.clearHistory(update.getMessage().getChatId());
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(CLEAR_HISTORY_MESSAGE)
                .build();
    }

    @Override
    public TelegramCommands getSupportedCommand() {
        return TelegramCommands.CLEAR_COMMAND;
    }
}
