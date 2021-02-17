package rybetsky.bosslang.bot.handlers.commands;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import rybetsky.bosslang.bot.Context;
import rybetsky.bosslang.bot.handlers.AbstractHandler;
import rybetsky.bosslang.bot.handlers.utils.InlineKeyboardBuilder;
import rybetsky.bosslang.domain.Language;
import rybetsky.bosslang.domain.UserEntity;

public class SettingsCommand extends AbstractHandler {

    public SettingsCommand() {
        super("/settings");
    }

    @Override
    public BotApiMethod<?> action(Context context) {
        UserEntity user = context.getUser();
        Language language = user.getLanguage();
        SendMessage message = new SendMessage(
                user.getChatId().toString(),
                getMessageService().getMessage(
                        "lang.setting-message",
                        language.getTag()
                )
        );
        message.setReplyMarkup(createInlineKeyboard(language));
        return message;
    }

    public InlineKeyboardMarkup createInlineKeyboard(Language lang) {
        InlineKeyboardBuilder builder = new InlineKeyboardBuilder();
        Language[] languages = Language.values();
        int i = 1;
        for (Language language : languages) {
            if (language == Language.Default) {
                continue;
            }
            if (i % 6 == 0) {
                builder.addButtonsToRow();
            }
            String langStr = language.toString();
            String text = lang == Language.English ?
                    langStr :
                    getMessageService().getMessage(
                            langStr,
                            lang.getTag()
                    );
            builder.addButton(text, "lang|" + langStr);
            i++;
        }
        builder.addButtonsToRow();
        return builder.build();
    }
}
