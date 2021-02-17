package rybetsky.bosslang.bot.handlers.callbacks;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import rybetsky.bosslang.bot.Context;
import rybetsky.bosslang.bot.handlers.AbstractHandler;
import rybetsky.bosslang.bot.handlers.utils.LanguagesDispatcher;
import rybetsky.bosslang.domain.Language;
import rybetsky.bosslang.domain.UserEntity;

public class ChangeLangCallback extends AbstractHandler {

    public ChangeLangCallback() {
        super("lang");
    }

    @Override
    public BotApiMethod<?> action(Context context) {
        UserEntity userEntity = context.getUser();
        CallbackQuery callbackQuery = context.getUpdate().getCallbackQuery();
        String[] languages = callbackQuery.getData().split("\\|");
        BotApiMethod<?> message;
        if (languages.length >= 2) {
            Language language = new LanguagesDispatcher().getLangByString(languages[1]);
            if (language == null) {
                language = Language.English;
            }
            userEntity.setLanguage(language);
            message = answerCallbackQuery("Edited", false, callbackQuery);
        } else {
            message = answerCallbackQuery("Wrong Language!", true, callbackQuery);
        }
        return message;
    }


}
