package rybetsky.bosslang.bot.handlers;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
public abstract class AbstractHandler implements QueryHandler {
    private final String handlerName;

    public AbstractHandler(String handlerName) {
        this.handlerName = handlerName;
    }

    public abstract BotApiMethod<?> action(Update update);
}
