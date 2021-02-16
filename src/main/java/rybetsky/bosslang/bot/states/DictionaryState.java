package rybetsky.bosslang.bot.states;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public class DictionaryState extends BaseState {

    public DictionaryState() {
        super(StatesIdentifiers.DICT);
        setNextSate(this);
    }

    @Override
    public BotApiMethod<?> action(Update update) {
        return null;
    }
}
