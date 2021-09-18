package ru.sugarisboy.home.core.station.starter.api;

import ru.sugarisboy.home.core.station.starter.api.dto.StationCommand;
import ru.sugarisboy.home.core.station.starter.api.dto.StationState;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class StationApi {

    private static StationWsConnection wsConnection;

    @Getter
    @Setter
    private StationState state;

    public boolean hasState() {
        return state != null;
    }

    public void send(StationCommand command) {
        wsConnection.sendCommand(command);
    }

    /*
     *
     */

    static {
        try {
            wsConnection = new StationWebSocketClient().createConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class SingletonHolder {
        public static final StationApi HOLDER_INSTANCE = new StationApi();
    }

    public static StationApi getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }
}
