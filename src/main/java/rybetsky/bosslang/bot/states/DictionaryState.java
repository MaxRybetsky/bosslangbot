package rybetsky.bosslang.bot.states;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import rybetsky.bosslang.bot.Context;
import rybetsky.bosslang.service.translator.Translator;
import rybetsky.bosslang.utils.ApplicationContextUtils;

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
        String sourceText = message.getText();
        String translatedText = translator.translateFromAnyToRussian(sourceText);
        if(sourceText.equals(translatedText)) {
            translatedText = translator.translateFromAnyToEnglish(sourceText);
        }
        SendMessage translation = new SendMessage(
                message.getChatId().toString(),
                translatedText
        );
        translation.setReplyToMessageId(message.getMessageId());
        return translation;
    }
}
