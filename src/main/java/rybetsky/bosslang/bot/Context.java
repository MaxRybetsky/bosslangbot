package rybetsky.bosslang.bot;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;
import rybetsky.bosslang.domain.UserEntity;

@Getter
public class Context {
    private final UserEntity user;
    private final Update update;

    public Context(UserEntity user, Update update) {
        this.user = user;
        this.update = update;
    }
}
