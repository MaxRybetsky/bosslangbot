package rybetsky.bosslang.bot.states;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import rybetsky.bosslang.bot.Context;
import rybetsky.bosslang.domain.UserEntity;

public class StartState extends BaseState {

    public StartState() {
        super(StatesIdentifiers.START);
        setNextSate(new DictionaryState());
    }

    @Override
    public BotApiMethod<?> action(Context context) {
        UserEntity user = context.getUser();
        String greetings = getMessageService().getMessage(
                "welcome.greetings",
                user.getLanguage().getTag(),
                user.getFirstName()
        );
        SendMessage message = new SendMessage(
                context.getUpdate().getMessage().getChatId().toString(),
                greetings
        );
        context.getUser().setState(getNextSate().getStateId());
        return message;
    }
}
