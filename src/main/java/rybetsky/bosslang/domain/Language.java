package rybetsky.bosslang.domain;

import lombok.Getter;

@Getter
public enum Language {
    English("en-US"),
    Russian("ru-RU");

    private String tag;

    private Language(String tag) {
        this.tag = tag;
    }
}
