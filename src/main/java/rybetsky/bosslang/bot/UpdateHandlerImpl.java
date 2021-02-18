package rybetsky.bosslang.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import rybetsky.bosslang.bot.handlers.HandlerDispatcher;
import rybetsky.bosslang.bot.states.StatesDispatcher;
import rybetsky.bosslang.domain.UserEntity;
import rybetsky.bosslang.service.UserService;

@Component
public class UpdateHandlerImpl implements UpdateHandler {
    private final UserService userService;

    @Autowired
    public UpdateHandlerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public BotApiMethod<?> handUpdate(Update update) {
        if ((update.hasMessage() && update.getMessage().hasText())
                || update.hasCallbackQuery()) {
            UserEntity userEntity = userService.findByUpdateOrCreateNew(update);
            Context context = new Context(userEntity, update);
            BotApiMethod<?> response = new HandlerDispatcher().handUpdateIfQueryOrCommand(context);
            if (response != null) {
                userService.save(userEntity);
                return response;
            }
            response = new StatesDispatcher().action(context);
            userService.save(userEntity);
            return response;
        }
        return null;
    }
}
