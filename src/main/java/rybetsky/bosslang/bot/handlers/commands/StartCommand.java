package rybetsky.bosslang.bot.handlers.commands;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import rybetsky.bosslang.bot.handlers.AbstractHandler;

public class StartCommand extends AbstractHandler {

    public StartCommand() {
        super("/start");
    }

    @Override
    public BotApiMethod<?> action(Update update) {
        SendMessage message = new SendMessage(
                update.getMessage().getChatId().toString(),
                "Hello!"
        );
        return message;
    }
}
