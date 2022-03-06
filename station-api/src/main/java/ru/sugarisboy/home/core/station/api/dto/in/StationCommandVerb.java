package ru.sugarisboy.home.core.station.api.dto.in;

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
    SEND_TEXT("sendText"),
    CHANGE_VOLUME("setVolume");

    @Getter
    private String commandNameApi = null;

}
