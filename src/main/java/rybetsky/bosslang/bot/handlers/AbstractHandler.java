package rybetsky.bosslang.bot.handlers;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import rybetsky.bosslang.bot.Context;

@Getter
public abstract class AbstractHandler implements QueryHandler {

    private final String handlerName;

    public AbstractHandler(String handlerName) {
        this.handlerName = handlerName;
    }

    public abstract BotApiMethod<?> action(Context context);
}
