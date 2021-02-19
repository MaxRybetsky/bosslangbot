package rybetsky.bosslang.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.meta.api.objects.Update;
import rybetsky.bosslang.dao.UserDao;
import rybetsky.bosslang.domain.UserEntity;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserDao userDao;

    @Mock
    private Update update;

    @Test
    public void findByUpdateWhenUpdateHasCallbackQuery() {
        UserEntity user = new UserEntity();
        user.setChatId(1L);
        Mockito.doReturn(user)
                .when(userDao)
                .findById(1L);
        Mockito.doReturn(true)
                .when(update)
                .hasCallbackQuery();
        Mockito.doReturn(1L)
                .when(update)
                .getCallbackQuery()
                .getMessage()
                .getChatId();
        UserEntity userEntity = userService.findByUpdateOrCreateNew(update);
        Assert.assertNotNull(userEntity);
    }
}