package rybetsky.bosslang.service;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.meta.api.objects.*;
import rybetsky.bosslang.bot.states.StatesIdentifiers;
import rybetsky.bosslang.dao.UserDao;
import rybetsky.bosslang.domain.Language;
import rybetsky.bosslang.domain.UserEntity;

import java.util.Optional;

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
        user.setUsername("test");
        user.setFirstName("fName");
        user.setLastName("lName");
        Optional<UserEntity> userOptional = Optional.of(user);
        Mockito.doReturn(userOptional)
                .when(userDao)
                .findById(1L);
        Mockito.doReturn(true)
                .when(update)
                .hasCallbackQuery();
        CallbackQuery callbackQuery = new CallbackQuery();
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        callbackQuery.setMessage(message);
        Mockito.doReturn(callbackQuery)
                .when(update)
                .getCallbackQuery();
        UserEntity userEntity = userService.findByUpdateOrCreateNew(update);
        Mockito.verify(userDao, Mockito.times(1))
                .findById(Mockito.anyLong());
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getChatId())
                        .matches(1L)
        );
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getFirstName())
                        .matches("fName")
        );
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getLastName())
                        .matches("lName")
        );
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getUsername())
                        .matches("test")
        );
    }

    @Test
    public void createNewUserByUpdateWithCallbackQuery() {
        Mockito.doReturn(Optional.empty())
                .when(userDao)
                .findById(2L);
        Mockito.doReturn(true)
                .when(update)
                .hasCallbackQuery();
        CallbackQuery callbackQuery = new CallbackQuery();
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(2L);
        message.setChat(chat);
        User telegramUser = new User(1, "fName", false,
                "lName", "username", "en",
                false, false, false);
        message.setFrom(telegramUser);
        callbackQuery.setMessage(message);
        Mockito.doReturn(callbackQuery)
                .when(update)
                .getCallbackQuery();
        UserEntity userEntity = userService.findByUpdateOrCreateNew(update);
        Mockito.verify(userDao, Mockito.times(1))
                .save(userEntity);
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getChatId())
                        .matches(2L)
        );
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getFirstName())
                        .matches("fName")
        );
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getLastName())
                        .matches("lName")
        );
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getUsername())
                        .matches("username")
        );
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getState())
                        .matches(StatesIdentifiers.START)
        );
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getLanguage())
                        .matches(Language.English)
        );
    }

    @Test
    public void findUserWhenUpdateContainsMessage() {
        UserEntity user = new UserEntity();
        user.setChatId(1L);
        user.setUsername("test");
        user.setFirstName("fName");
        user.setLastName("lName");
        Optional<UserEntity> userOptional = Optional.of(user);
        Mockito.doReturn(userOptional)
                .when(userDao)
                .findById(1L);
        Mockito.doReturn(true)
                .when(update)
                .hasMessage();
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        Mockito.doReturn(message)
                .when(update)
                .getMessage();
        UserEntity userEntity = userService.findByUpdateOrCreateNew(update);
        Assert.assertNotNull(userEntity);
        Mockito.verify(userDao, Mockito.times(1))
                .findById(Mockito.anyLong());
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getChatId())
                        .matches(1L)
        );
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getFirstName())
                        .matches("fName")
        );
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getLastName())
                        .matches("lName")
        );
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getUsername())
                        .matches("test")
        );
    }

    @Test
    public void createNewUserByUpdateWithMessage() {
        Mockito.doReturn(Optional.empty())
                .when(userDao)
                .findById(2L);
        Mockito.doReturn(true)
                .when(update)
                .hasMessage();
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(2L);
        message.setChat(chat);
        User telegramUser = new User(1, "fName", false,
                "lName", "username", "en",
                false, false, false);
        message.setFrom(telegramUser);
        Mockito.doReturn(message)
                .when(update)
                .getMessage();
        UserEntity userEntity = userService.findByUpdateOrCreateNew(update);
        Mockito.verify(userDao, Mockito.times(1))
                .save(userEntity);
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getChatId())
                        .matches(2L)
        );
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getFirstName())
                        .matches("fName")
        );
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getLastName())
                        .matches("lName")
        );
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getUsername())
                        .matches("username")
        );
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getState())
                        .matches(StatesIdentifiers.START)
        );
        Assert.assertTrue(
                CoreMatchers.is(userEntity.getLanguage())
                        .matches(Language.English)
        );
    }
}