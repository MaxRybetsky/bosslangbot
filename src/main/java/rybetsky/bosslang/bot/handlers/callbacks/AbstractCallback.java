package rybetsky.bosslang.bot.handlers.callbacks;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import rybetsky.bosslang.bot.Context;
import rybetsky.bosslang.bot.handlers.QueryHandler;
import rybetsky.bosslang.service.LocalMessageService;
import rybetsky.bosslang.utils.ApplicationContextUtils;

@Getter
@Setter
public abstract class AbstractCallback implements QueryHandler {
    private final String callbackName;
    private final LocalMessageService messageService;

    public AbstractCallback(String callbackName) {
        this.callbackName = callbackName;
        messageService = ApplicationContextUtils.getMessageService();
    }

    public abstract BotApiMethod<?> action(Context context);

    @Override
    public String getHandlerName() {
        return callbackName;
    }

    protected AnswerCallbackQuery answerCallbackQuery(String text, boolean alert,
                                                      CallbackQuery callbackQuery) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackQuery.getId());
        answerCallbackQuery.setShowAlert(alert);
        answerCallbackQuery.setText(text);
        return answerCallbackQuery;
    }
}
