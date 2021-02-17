package rybetsky.bosslang.bot.handlers.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardBuilder {
    private final InlineKeyboardMarkup inlineKeyboardMarkup;
    private List<InlineKeyboardButton> buttons = new ArrayList<>();
    private final List<List<InlineKeyboardButton>> rows = new ArrayList<>();

    public InlineKeyboardBuilder() {
        this.inlineKeyboardMarkup = new InlineKeyboardMarkup();
    }

    public InlineKeyboardMarkup getInlineKeyboardMarkup() {
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardBuilder addButton(String text, String callBackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callBackData);
        buttons.add(button);
        return this;
    }

    public InlineKeyboardBuilder addButtonsToRow() {
        rows.add(buttons);
        buttons = new ArrayList<>();
        return this;
    }

    public InlineKeyboardMarkup build() {
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }
}
