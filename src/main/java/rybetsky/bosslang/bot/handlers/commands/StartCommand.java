package rybetsky.bosslang.bot.handlers.commands;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import rybetsky.bosslang.bot.Context;
import rybetsky.bosslang.bot.states.BaseState;
import rybetsky.bosslang.bot.states.StartState;
import rybetsky.bosslang.bot.states.StatesIdentifiers;
import rybetsky.bosslang.domain.UserEntity;

public class StartCommand extends AbstractCommand {
    public StartCommand() {
        super("/start");
    }

    @Override
    public BotApiMethod<?> action(Context context) {
        UserEntity user = context.getUser();
        BotApiMethod<?> message;
        if (user.getState() == StatesIdentifiers.START) {
            BaseState state = new StartState();
            message = state.action(context);
        } else {
            message = new SendMessage(
                    user.getChatId().toString(),
                    getMessageService().getMessage(
                            "welcome.already-started",
                            user.getLanguage().getTag()
                    )
            );
        }
        return message;
    }
}
