package rybetsky.bosslang.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import rybetsky.bosslang.bot.handlers.HandlerDispatcher;
import rybetsky.bosslang.bot.handlers.commands.StartCommand;
import rybetsky.bosslang.bot.states.StatesDispatcher;
import rybetsky.bosslang.bot.states.StatesIdentifiers;
import rybetsky.bosslang.domain.Language;
import rybetsky.bosslang.domain.UserEntity;
import rybetsky.bosslang.service.UserService;

import java.util.Optional;

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
            UserEntity userEntity = getUser(update);
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

    private UserEntity getUser(Update update) {
        Long chatId;
        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
        } else {
            chatId = update.getMessage().getChatId();
        }
        Optional<UserEntity> userEntityOptional = userService.findById(chatId);
        return userEntityOptional.orElseGet(() -> newUserByUpdate(update));
    }

    private UserEntity newUserByUpdate(Update update) {
        Long chatId = update.getMessage().getChatId();
        User user = update.getMessage().getFrom();
        UserEntity newUser = new UserEntity(
                chatId,
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                StatesIdentifiers.START,
                Language.English
        );
        userService.save(newUser);
        return newUser;
    }
}
