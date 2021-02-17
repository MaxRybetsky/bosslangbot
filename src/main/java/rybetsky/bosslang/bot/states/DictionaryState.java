package rybetsky.bosslang.bot.states;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import rybetsky.bosslang.bot.Context;

public class DictionaryState extends BaseState {

    public DictionaryState() {
        super(StatesIdentifiers.DICT);
        setNextSate(this);
    }

    @Override
    public BotApiMethod<?> action(Context context) {
        Update update = context.getUpdate();
        SendMessage message = new SendMessage(
                update.getMessage().getChatId().toString(),
                update.getMessage().getText()
        );
        return message;
    }
}
