package ru.sugarisboy.home.core.station.api;

import ru.sugarisboy.home.core.station.api.dto.in.StationCommand;
import ru.sugarisboy.home.core.station.api.dto.in.StationRequestPayloadBuilder;
import ru.sugarisboy.home.core.station.api.dto.out.StationState;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class StationApi {

    private static StationWsConnection wsConnection;

    private boolean isConnected;

    @Setter
    private StationState state;

    public void createConnect() {
        try {
            if (!isConnected) {
                wsConnection = new StationWebSocketClient().createConnection();
                isConnected = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            isConnected = false;
        }
    }

    public boolean hasState() {
        return state != null;
    }

    public StationRequestPayloadBuilder command() {
        return StationCommand.build(wsConnection).command();
    }

    public void closeConnection() {
        wsConnection.close();
        isConnected = false;
    }

    private static class SingletonHolder {
        public static final StationApi HOLDER_INSTANCE = new StationApi();
    }

    public static StationApi getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }
}
