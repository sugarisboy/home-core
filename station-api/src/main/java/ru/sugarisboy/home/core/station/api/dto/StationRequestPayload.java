package ru.sugarisboy.home.core.station.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PACKAGE)
public class StationRequestPayload {

    private final String command;
    private final int position;
    private final String type;
    private final String id;
    private final float volume;
    private final String text;
}
