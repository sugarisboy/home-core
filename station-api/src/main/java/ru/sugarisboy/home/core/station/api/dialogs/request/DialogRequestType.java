package ru.sugarisboy.home.core.station.api.dialogs.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DialogRequestType {

    SIMPLE_UTTERANCE("SimpleUtterance"),
    BUTTON_PRESSED("ButtonPressed");

    @Getter
    private final String raw;

    @JsonCreator
    public static DialogRequestType forName(String name) {
        if (name.equals(""))
            return null;

        for (DialogRequestType c : values()) {
            if (c.getRaw().equalsIgnoreCase(name)) {
                return c;
            }
        }

        throw new RuntimeException("Not found value for enum: " + name);
    }
}
