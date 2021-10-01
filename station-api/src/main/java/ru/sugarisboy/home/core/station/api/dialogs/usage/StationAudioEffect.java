package ru.sugarisboy.home.core.station.api.dialogs.usage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StationAudioEffect {

    TRAIN_STATION("train_announce");

    @Getter
    private final String raw;
}
