package rybetsky.bosslang.service.translator;

import rybetsky.bosslang.domain.Language;

public interface Translator {
    String translate(Language source, Language target, String text);

    default String translate(Language target, String text) {
        return translate(Language.Default, target, text);
    }

    default String translateFromAnyToRussian(String text) {
        return translate(Language.Russian, text);
    }

    default String translateFromAnyToEnglish(String text) {
        return translate(Language.English, text);
    }
}
