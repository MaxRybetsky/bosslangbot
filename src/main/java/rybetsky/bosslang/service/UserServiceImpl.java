package rybetsky.bosslang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rybetsky.bosslang.dao.UserDao;
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
    public Iterable<UserEntity> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(UserEntity userEntity) {
        userDao.save(userEntity);
    }
}
