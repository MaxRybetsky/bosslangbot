package rybetsky.bosslang.bot.handlers;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import rybetsky.bosslang.bot.handlers.commands.SettingsCommand;
import rybetsky.bosslang.bot.handlers.commands.StartCommand;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class HandlerDispatcher {
    private Map<String, AbstractHandler> dispatcher;

    public HandlerDispatcher() {
        init();
    }

    private void init() {
        AbstractHandler startCommand = new StartCommand();
        AbstractHandler settingsCommand = new SettingsCommand();
        addCommands(startCommand, settingsCommand);
    }

    private void addCommands(AbstractHandler... handlers) {
        dispatcher = Arrays.stream(handlers)
                .collect(
                        Collectors.toMap(
                                AbstractHandler::getHandlerName,
                                handler -> handler
                        ));
    }

    public BotApiMethod<?> handUpdateIfQueryOrCommand(Update update) {
        String key;
        if(update.hasCallbackQuery()) {
            key = update.getCallbackQuery().getData();
        } else if(update.hasMessage() && update.getMessage().hasText()) {
            key = update.getMessage().getText();
        } else {
            return null;
        }
        return handIfExists(key, update);
    }

    private BotApiMethod<?> handIfExists(String key, Update update) {
        AbstractHandler command = dispatcher.get(key);
        if (command == null) {
            return null;
        }
        return command.action(update);
    }
}
