package rybetsky.bosslang.bot.states;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import rybetsky.bosslang.bot.Context;

public class StartState extends BaseState {

    public StartState() {
        super(StatesIdentifiers.START);
    }

    @Override
    public BotApiMethod<?> action(Context context) {
        SendMessage message = new SendMessage(
                context.getUpdate().getMessage().getChatId().toString(),
                "Hello, " + context.getUser().getFirstName()
        );
        setNextSate(new DictionaryState());
        return message;
    }
}
