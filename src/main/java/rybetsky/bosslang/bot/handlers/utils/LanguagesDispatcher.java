package rybetsky.bosslang.bot.handlers.utils;

import rybetsky.bosslang.domain.Language;

import java.util.HashMap;
import java.util.Map;

public class LanguagesDispatcher {
    Map<String, Language> dispatcher = new HashMap<>();

    public LanguagesDispatcher() {
        init();
    }

    private void init() {
        dispatcher.put("English", Language.English);
        dispatcher.put("Russian", Language.Russian);
    }

    public Language getLangByString(String lang) {
        return dispatcher.get(lang);
    }
}
