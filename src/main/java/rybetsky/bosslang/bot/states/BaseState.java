package rybetsky.bosslang.bot.states;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
@Setter
public abstract class BaseState {
    private final StatesIdentifiers state;

    private BaseState nextSate;

    public BaseState(StatesIdentifiers state) {
        this.state = state;
    }

    public abstract BotApiMethod<?> action(Update update);
}
