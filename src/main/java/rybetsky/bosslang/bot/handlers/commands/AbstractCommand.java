package rybetsky.bosslang.bot.handlers.commands;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import rybetsky.bosslang.bot.Context;
import rybetsky.bosslang.bot.handlers.QueryHandler;
import rybetsky.bosslang.service.LocalMessageService;
import rybetsky.bosslang.utils.ApplicationContextUtils;

@Getter
public abstract class AbstractCommand implements QueryHandler {

    private final String commandName;
    private final LocalMessageService messageService;

    public AbstractCommand(String commandName) {
        this.commandName = commandName;
        messageService = ApplicationContextUtils.getMessageService();
    }

    public abstract BotApiMethod<?> action(Context context);

    @Override
    public String getHandlerName() {
        return commandName;
    }
}
