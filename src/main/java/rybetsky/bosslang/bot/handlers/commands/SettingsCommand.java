package rybetsky.bosslang.bot.handlers.commands;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import rybetsky.bosslang.bot.handlers.AbstractHandler;

public class SettingsCommand extends AbstractHandler {

    public SettingsCommand() {
        super("/settings");
    }

    @Override
    public BotApiMethod<?> action(Update update) {
        return null;
    }
}
