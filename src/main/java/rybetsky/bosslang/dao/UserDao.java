package rybetsky.bosslang.dao;

import org.springframework.data.repository.CrudRepository;
import rybetsky.bosslang.domain.UserEntity;

public interface UserDao extends CrudRepository<UserEntity, Long> {
}
