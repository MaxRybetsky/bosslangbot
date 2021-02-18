package rybetsky.bosslang.bot.handlers;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import rybetsky.bosslang.bot.Context;

public interface QueryHandler {
    BotApiMethod<?> action(Context context);

    String getHandlerName();
}
