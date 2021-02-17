package rybetsky.bosslang.bot.handlers;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import rybetsky.bosslang.bot.Context;

@Getter
public abstract class AbstractHandler implements QueryHandler {

    private final String handlerName;

    public AbstractHandler(String handlerName) {
        this.handlerName = handlerName;
    }

    public abstract BotApiMethod<?> action(Context context);

    protected AnswerCallbackQuery answerCallbackQuery(String text, boolean alert,
                                                      CallbackQuery callbackQuery) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackQuery.getId());
        answerCallbackQuery.setShowAlert(alert);
        answerCallbackQuery.setText(text);
        return answerCallbackQuery;
    }
}
