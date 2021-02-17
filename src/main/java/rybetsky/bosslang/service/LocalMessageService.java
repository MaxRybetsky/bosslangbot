package rybetsky.bosslang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalMessageService {
    private final MessageSource messageSource;

    @Autowired
    public LocalMessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String message, String localeTag) {
        return getMessage(message, localeTag, null);
    }

    public String getMessage(String message, String localeTag, Object... args) {
        Locale locale = Locale.forLanguageTag(localeTag);
        return messageSource.getMessage(message, args, locale);
    }

}
