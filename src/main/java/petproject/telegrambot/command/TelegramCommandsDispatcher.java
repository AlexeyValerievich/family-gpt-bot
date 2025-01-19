package petproject.telegrambot.command;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Service
@AllArgsConstructor
public class TelegramCommandsDispatcher {

    private final List<TelegramCommandHandler> telegramCommandHandlerList;

    public BotApiMethod<?> processCommand(Update update) {
        if(!isCommand(update)) {
            throw new IllegalArgumentException("Update is not a valid command");
        }
        var text = update.getMessage().getText();
        var suitedHandler = telegramCommandHandlerList.stream()
                .filter(it -> it.getSupportedCommand().getCommandValue().equals(text))
                .findAny();

        if(suitedHandler.isEmpty()) {
                return SendMessage.builder()
                        .chatId(update.getMessage().getChatId())
                        .text("No support command^ command=%s".formatted(text))
                        .build();
        }

        return suitedHandler.orElseThrow().processCommand(update);
    }

    public boolean isCommand(Update update) {
        return update.hasMessage()
                && update.getMessage().hasText()
                && update.getMessage().getText().startsWith("/");
    }
}
