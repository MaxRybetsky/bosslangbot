package rybetsky.bosslang.bot.states;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import rybetsky.bosslang.bot.Context;
import rybetsky.bosslang.service.LocalMessageService;
import rybetsky.bosslang.utils.ApplicationContextUtils;

@Getter
@Setter
public abstract class BaseState {
    private final StatesIdentifiers stateId;
    private final LocalMessageService messageService;

    private BaseState nextSate;

    public BaseState(StatesIdentifiers stateId) {
        this.stateId = stateId;
        messageService = ApplicationContextUtils.getMessageService();
    }

    public abstract BotApiMethod<?> action(Context context);
}
