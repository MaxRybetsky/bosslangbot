package rybetsky.bosslang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import rybetsky.bosslang.bot.states.StatesIdentifiers;
import rybetsky.bosslang.dao.UserDao;
import rybetsky.bosslang.domain.Language;
import rybetsky.bosslang.domain.UserEntity;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<UserEntity> findById(Long chatId) {
        return userDao.findById(chatId);
    }

    @Override
    public UserEntity findByUpdateOrCreateNew(Update update) {
        Long chatId;
        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
        } else {
            chatId = update.getMessage().getChatId();
        }
        Optional<UserEntity> userEntityOptional = userDao.findById(chatId);
        return userEntityOptional.orElseGet(() -> newUserByUpdate(update));
    }

    @Override
    public Iterable<UserEntity> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(UserEntity userEntity) {
        userDao.save(userEntity);
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
        userDao.save(newUser);
        return newUser;
    }
}
