package ru.sugarisboy.home.core.station.api;

import ru.sugarisboy.home.core.station.api.dto.StationClientResponse;
import ru.sugarisboy.home.core.station.api.dto.StationCommand;
import ru.sugarisboy.home.core.station.api.dto.StationState;
import ru.sugarisboy.home.core.station.api.mapper.StationResponseToStateMapper;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.util.Map;
import javax.websocket.ClientEndpoint;

@Slf4j
@ClientEndpoint
class StationWsConnection extends WebSocketClient {

    private final StationApi api = StationApi.getInstance();
    private final StationResponseToStateMapper mapper = new StationResponseToStateMapper();

    private String token;

    public StationWsConnection(URI serverUri) {
        super(serverUri, Map.of("Origin", "http://yandex.ru/"));
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        log.info("Connected to station");
    }

    @Override
    public void onMessage(String message) {
        log.debug("Data received from the station ");
        StationClientResponse responseDto = mapper.convertToDto(message);
        StationState state = responseDto.getState();
        api.setState(state);
        log.debug("Station state was updated: {}", state);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("Disconnected to station: " + reason + ", remote = " + remote);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public void authorize(StationCommand request, String token) {
        this.token = token;
        this.sendCommand(request);
    }

    public void sendCommand(StationCommand request) {
        this.send(mapper.toBytes(request.withToken(token)));
    }

    @Override
    public void send(String text) {
        log.debug("Send to station: {}", text);
        super.send(text);
    }
}
