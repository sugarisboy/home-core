package ru.sugarisboy.home.core.station.api.dto.in;

import ru.sugarisboy.home.core.station.api.StationWsConnection;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.UUID;

@Data
public class StationCommand {

    private final UUID id;
    private final Long sentTime;
    private final StationRequestPayload payload;

    private String conversationToken;

    private StationCommand(StationRequestPayload payload) {
        this.payload = payload;
        this.id = UUID.randomUUID();
        this.sentTime = System.currentTimeMillis()  * 1000000;
    }

    @Accessors(fluent = true)
    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public static class Builder {
        private final StationWsConnection wsConnection;

        private StationRequestPayload payload;

        public StationRequestPayloadBuilder command() {
            return StationRequestPayload.builder(this);
        }

        public Builder payload(StationRequestPayload payload) {
            this.payload = payload;
            return this;
        }

        public void send() {
            StationCommand command = new StationCommand(payload);
            wsConnection.sendCommand(command);
        }
    }

    public static Builder build(StationWsConnection wsConnection) {
        return new Builder(wsConnection);
    }
}
