package rybetsky.bosslang.service.translator;

import rybetsky.bosslang.domain.Language;

public interface Translator {
    String translate(Language source, Language target, String text) throws Exception;

    default String translate(Language target, String text) throws Exception {
        return translate(Language.Default, target, text);
    }

    default String translateFromAnyToRussian(String text) throws Exception {
        return translate(Language.Russian, text);
    }

    default String translateFromAnyToEnglish(String text) throws Exception {
        return translate(Language.English, text);
    }
}
