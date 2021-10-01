package ru.sugarisboy.home.core.station.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StationPlayerStateType {

    TRACK;

    @JsonCreator
    public static StationPlayerStateType forName(String name) {
        if (name == "")
            return null;

        for (StationPlayerStateType c : values()) {
            if (c.name().equals(name.toUpperCase())) {
                return c;
            }
        }

        throw new RuntimeException("Not found value for enum: " + name);
    }
}
