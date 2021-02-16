package rybetsky.bosslang.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import rybetsky.bosslang.bot.handlers.commands.StartCommand;
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
//        if(update.hasMessage() && update.getMessage().hasText()) {
//            UserEntity userEntity = getUser(update);
//
//        }
        return new StartCommand().action(update);
    }

    private UserEntity getUser(Update update) {
        Long chatId = update.getMessage().getChatId();
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
                StatesIdentifiers.DICT,
                Language.English
        );
        userService.save(newUser);
        return newUser;
    }
}
