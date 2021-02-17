package rybetsky.bosslang.bot.handlers.callbacks;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import rybetsky.bosslang.bot.Context;
import rybetsky.bosslang.bot.handlers.AbstractHandler;

public class ChangeLangCallback extends AbstractHandler {

    public ChangeLangCallback() {
        super("lang");
    }

    @Override
    public BotApiMethod<?> action(Context context) {
        AnswerCallbackQuery message = new AnswerCallbackQuery();
        message.setCallbackQueryId(context.getUpdate().getCallbackQuery().getId());
        message.setShowAlert(false);
        message.setText("Edited!");
        return message;
    }
}
