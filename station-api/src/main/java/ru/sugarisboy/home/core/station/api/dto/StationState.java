package ru.sugarisboy.home.core.station.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationState {

    private PlayerState playerState;
    private StationAliceState aliceState;
    private boolean playing;
    private int timeSinceLastVoiceActivity;
    private float volume;
}
