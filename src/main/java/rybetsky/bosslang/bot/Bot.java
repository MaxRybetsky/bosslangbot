package rybetsky.bosslang.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Bot extends TelegramWebhookBot {
    private final String botUsername;
    private final String botToken;
    private final String botPath;

    private final UpdateHandler handler;

    public Bot(
            @Value("${telegram.name}") String botUsername,
            @Value("${telegram.token}") String botToken,
            @Value("${telegram.path}") String botPath,
            @Autowired UpdateHandler handler) {
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.botPath = botPath;
        this.handler = handler;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return botPath;
    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        return handler.handUpdate(update);
    }
}
