package rybetsky.bosslang.bot.handlers;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import rybetsky.bosslang.bot.Context;
import rybetsky.bosslang.bot.handlers.callbacks.ChangeLangCallback;
import rybetsky.bosslang.bot.handlers.commands.SettingsCommand;
import rybetsky.bosslang.bot.handlers.commands.StartCommand;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class HandlerDispatcher {
    private Map<String, QueryHandler> dispatcher;

    public HandlerDispatcher() {
        init();
    }

    private void init() {
        QueryHandler startCommand = new StartCommand();
        QueryHandler settingsCommand = new SettingsCommand();
        QueryHandler langCallback = new ChangeLangCallback();
        addCommands(startCommand, settingsCommand, langCallback);
    }

    private void addCommands(QueryHandler... handlers) {
        dispatcher = Arrays.stream(handlers)
                .collect(
                        Collectors.toMap(
                                QueryHandler::getHandlerName,
                                handler -> handler
                        ));
    }

    public BotApiMethod<?> handUpdateIfQueryOrCommand(Context context) {
        String key;
        Update update = context.getUpdate();
        if(update.hasCallbackQuery()) {
            key = update.getCallbackQuery().getData().split("\\|")[0];
        } else if(update.hasMessage() && update.getMessage().hasText()) {
            key = update.getMessage().getText();
        } else {
            return null;
        }
        return handIfExists(key, context);
    }

    private BotApiMethod<?> handIfExists(String key, Context context) {
        QueryHandler command = dispatcher.get(key);
        if (command == null) {
            return null;
        }
        return command.action(context);
    }
}
