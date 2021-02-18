package rybetsky.bosslang.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import rybetsky.bosslang.domain.UserEntity;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findById(Long chatId);

    UserEntity findByUpdateOrCreateNew(Update update);

    Iterable<UserEntity> findAll();

    void save(UserEntity userEntity);
}
