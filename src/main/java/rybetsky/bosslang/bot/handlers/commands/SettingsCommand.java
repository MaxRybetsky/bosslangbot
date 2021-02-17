package rybetsky.bosslang.bot.handlers.commands;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import rybetsky.bosslang.bot.Context;
import rybetsky.bosslang.bot.handlers.AbstractHandler;
import rybetsky.bosslang.bot.handlers.utils.InlineKeyboardBuilder;
import rybetsky.bosslang.domain.Language;

public class SettingsCommand extends AbstractHandler {

    public SettingsCommand() {
        super("/settings");
    }

    @Override
    public BotApiMethod<?> action(Context context) {
        SendMessage message = new SendMessage(
                context.getUser().getChatId().toString(),
                "Choose your language:"
        );
        message.setReplyMarkup(createInlineKeyboard());
        return message;
    }

    public InlineKeyboardMarkup createInlineKeyboard() {
        InlineKeyboardBuilder builder = new InlineKeyboardBuilder();
        Language[] languages = Language.values();
        int i = 1;
        for (Language language : languages) {
            if(language == Language.Default) {
                continue;
            }
            if (i % 6 == 0) {
                builder.addButtonsToRow();
            }
            String lang = language.toString();
            builder.addButton(lang, "lang|" + lang);
            i++;
        }
        builder.addButtonsToRow();
        return builder.build();
    }
}
