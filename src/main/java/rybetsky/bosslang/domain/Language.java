package rybetsky.bosslang.domain;

import lombok.Getter;

@Getter
public enum Language {
    Default("", ""),
    English("en-US", "en"),
    Russian("ru-RU", "ru");

    private final String tag;
    private final String code;

    private Language(String tag, String code) {
        this.tag = tag;
        this.code = code;
    }
}
