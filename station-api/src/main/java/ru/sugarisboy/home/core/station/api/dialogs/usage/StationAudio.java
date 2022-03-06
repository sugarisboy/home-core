package ru.sugarisboy.home.core.station.api.dialogs.usage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StationAudio {

    THINGS_EXPLOSION("alice-sounds-things-explosion-1.opus");

    @Getter
    private final String file;
}
