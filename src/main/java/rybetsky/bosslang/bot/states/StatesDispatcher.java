package rybetsky.bosslang.bot.states;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import rybetsky.bosslang.bot.Context;
import rybetsky.bosslang.domain.UserEntity;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class StatesDispatcher {
    private Map<StatesIdentifiers, BaseState> dispatcher;

    public StatesDispatcher() {
        init();
    }

    private void init() {
        BaseState start = new StartState();
        BaseState dict = new DictionaryState();
        addStates(start, dict);
    }

    private void addStates(BaseState... states) {
        dispatcher = Arrays.stream(states)
                .collect(
                        Collectors.toMap(
                                BaseState::getStateId,
                                state -> state
                        )
                );
    }

    public BotApiMethod<?> action(Context context) {
        UserEntity user = context.getUser();
        BaseState state = dispatcher.get(user.getState());
        return state.action(context);
    }
}
