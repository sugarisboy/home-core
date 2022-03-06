package ru.sugarisboy.home.core.station.api.dto.in;

import static ru.sugarisboy.home.core.station.api.dto.in.StationCommandVerb.CHANGE_VOLUME;
import static ru.sugarisboy.home.core.station.api.dto.in.StationCommandVerb.NEXT_TRACK;
import static ru.sugarisboy.home.core.station.api.dto.in.StationCommandVerb.PLAY;
import static ru.sugarisboy.home.core.station.api.dto.in.StationCommandVerb.PREV_TRACK;
import static ru.sugarisboy.home.core.station.api.dto.in.StationCommandVerb.SEND_TEXT;
import static ru.sugarisboy.home.core.station.api.dto.in.StationCommandVerb.STOP;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class StationRequestPayloadBuilder {

    private static final String LIKE = "Поставь лайк";
    private static final String UNLIKE = "Удали лайк";
    private static final String REPEAT_MESSAGE = "Повтори за мной %s";

    private final StationCommand.Builder parentBuilder;

    public StationCommand.Builder ping() {
        return parentBuilder;
    }

    public StationCommand.Builder repeatText(String text) {
        return execute(String.format(REPEAT_MESSAGE, text));
    }

    public StationCommand.Builder stop() {
        return asCommandWithOnlyVerb(STOP);
    }

    public StationCommand.Builder play() {
        return asCommandWithOnlyVerb(PLAY);
    }

    public StationCommand.Builder nextTrack() {
        return asCommandWithOnlyVerb(NEXT_TRACK);
    }

    public StationCommand.Builder prevTrack() {
        return asCommandWithOnlyVerb(PREV_TRACK);
    }

    public StationCommand.Builder like() {
        return execute(LIKE);
    }

    public StationCommand.Builder unlike() {
        return execute(UNLIKE);
    }

    public StationCommand.Builder execute(String command) {
        return parentBuilder.payload(
                asRequestWithCommand(SEND_TEXT)
                        .setText(command)
        );
    }

    public StationCommand.Builder changeVolume(float value) {
        if (value > 0.7) {
            throw new RuntimeException("Максимальная громкость 70%");
        }

        return parentBuilder.payload(
                asRequestWithCommand(CHANGE_VOLUME)
                        .setVolume(value)
        );
    }

    private StationCommand.Builder asCommandWithOnlyVerb(StationCommandVerb verb) {
        return parentBuilder.payload(asRequestWithCommand(verb));
    }

    private StationRequestPayload asRequestWithCommand(StationCommandVerb verb) {
        return new StationRequestPayload().setCommand(verb.getCommandNameApi());
    }

}
