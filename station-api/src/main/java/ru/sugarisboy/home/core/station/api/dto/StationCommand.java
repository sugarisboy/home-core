package ru.sugarisboy.home.core.station.api.dto;

import static ru.sugarisboy.home.core.station.api.dto.StationConstants.COMMAND_SEND_TEXT;
import static ru.sugarisboy.home.core.station.api.dto.StationConstants.REPEAT_MESSAGE;

import lombok.Data;
import java.util.UUID;

@Data
public class StationCommand {

    private final UUID id = UUID.randomUUID();
    private final long sentTime = System.currentTimeMillis() * 1000000;
    private final StationRequestPayload payload;

    private String conversationToken;

    private StationCommand(StationRequestPayload payload) {
        this.payload = payload;
    }

    public StationCommand withToken(String conversationToken) {
        this.conversationToken = conversationToken;
        return this;
    }

    public static StationCommand ping() {
        return new StationCommand(null);
    }

    public static StationCommand speak(String text) {
        return new StationCommand(
                StationRequestPayload.builder()
                        .command(COMMAND_SEND_TEXT)
                        .text(String.format(REPEAT_MESSAGE, text))
                        .build()
        );
    }

    public static StationCommand executeCustomCommand(String text) {
        return new StationCommand(
                StationRequestPayload.builder()
                        .command(COMMAND_SEND_TEXT)
                        .text(text)
                        .build()
        );
    }

    public static StationCommand executeCommand(StationCommandVerb verb) {
        switch (verb) {
            case NEXT_TRACK:
            case PREV_TRACK:
            case PLAY:
            case STOP:
                return executeinternalComamnd(verb);
        }
        return new StationCommand(
                StationRequestPayload.builder()
                        .command(verb.getCommandNameApi())
                        .build();
        )
    }

    private static StationCommand executeinternalComamnd(StationCommandVerb verb) {
        return new StationCommand(
                StationRequestPayload.builder()
                        .command(verb.getCommandNameApi())
                        .build()
        );
    }
}
