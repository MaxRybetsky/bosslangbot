package rybetsky.bosslang.bot.handlers;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface QueryHandler {
    BotApiMethod<?> action(Update update);
}
