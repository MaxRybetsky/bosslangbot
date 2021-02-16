package rybetsky.bosslang.service;

import rybetsky.bosslang.domain.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findById(Long chatId);

    Iterable<UserEntity> findAll();

    void save(UserEntity userEntity);
}
