package ru.sugarisboy.home.core.station.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum StationCommandVerb {

    PLAY("play"),
    STOP("stop"),
    NEXT_TRACK("next"),
    PREV_TRACK("prev"),
    LIKE,
    UNLIKE;

    @Getter
    private String commandNameApi = null;

}
