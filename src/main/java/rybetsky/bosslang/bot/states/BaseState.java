package rybetsky.bosslang.bot.states;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import rybetsky.bosslang.bot.Context;

@Getter
@Setter
public abstract class BaseState {
    private final StatesIdentifiers stateId;

    private BaseState nextSate;

    public BaseState(StatesIdentifiers stateId) {
        this.stateId = stateId;
    }

    public abstract BotApiMethod<?> action(Context context);
}
