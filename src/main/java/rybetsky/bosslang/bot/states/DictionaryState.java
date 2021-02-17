package rybetsky.bosslang.bot.states;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import rybetsky.bosslang.bot.Context;
import rybetsky.bosslang.service.translator.Translator;

import static rybetsky.bosslang.utils.ApplicationContextUtils.getTranslator;

public class DictionaryState extends BaseState {
    private final Translator translator;

    public DictionaryState() {
        super(StatesIdentifiers.DICT);
        setNextSate(this);
        translator = getTranslator();
    }

    @Override
    public BotApiMethod<?> action(Context context) {
        Message message = context.getUpdate().getMessage();
        SendMessage result = new SendMessage();
        result.setChatId(message.getChatId().toString());
        try {
            String sourceText = message.getText();
            String translatedText = translator.translateFromAnyToRussian(sourceText);
            if (sourceText.equals(translatedText)) {
                translatedText = translator.translateFromAnyToEnglish(sourceText);
            }
            result.setText(translatedText);
            result.setReplyToMessageId(message.getMessageId());
        } catch (Exception e) {
            result.setText(
                    getMessageService().getMessage(
                            "dict.error",
                            context.getUser().getLanguage().getTag()
                    )
            );
        }
        return result;
    }
}
