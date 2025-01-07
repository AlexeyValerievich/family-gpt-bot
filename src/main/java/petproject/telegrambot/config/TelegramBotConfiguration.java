package petproject.telegrambot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import petproject.telegrambot.TelegramBot;
import petproject.telegrambot.openai.OpenAIClient;

@Configuration
public class TelegramBotConfiguration {

    @Bean
    public TelegramBot telegramBot(
        @Value("${bot.token}") String botToken,
        TelegramBotsApi telegramBotsApi,
        OpenAIClient openAIClient
    ) {
        var botOptions = new DefaultBotOptions();
        var bot = new TelegramBot(botOptions, botToken, openAIClient);
        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка регистрации бота", e);
        }
        return bot;
    }

    @Bean
    public TelegramBotsApi telegramBotsApi() {
        try {
            return new TelegramBotsApi(DefaultBotSession.class);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка инициализации TelegramBotsApi", e);
        }
    }
}
