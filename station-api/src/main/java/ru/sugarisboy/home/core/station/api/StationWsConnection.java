package ru.sugarisboy.home.core.station.api;

import ru.sugarisboy.home.core.station.api.dto.out.StationClientResponse;
import ru.sugarisboy.home.core.station.api.dto.in.StationCommand;
import ru.sugarisboy.home.core.station.api.dto.out.StationState;
import ru.sugarisboy.home.core.station.api.mapper.StationResponseToStateMapper;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.util.Map;
import javax.websocket.ClientEndpoint;

@Slf4j
@ClientEndpoint
public class StationWsConnection extends WebSocketClient {

    private final StationApi api = StationApi.getInstance();
    private final StationResponseToStateMapper mapper = new StationResponseToStateMapper();

    private String token;

    StationWsConnection(URI serverUri, String token) {
        super(serverUri, Map.of("Origin", "http://yandex.ru/"));
        this.token = token;
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("Connected to station");
    }

    @Override
    public void onMessage(String message) {
        log.debug("Data received from the station ");
        System.out.println(message);
        StationClientResponse responseDto = mapper.convertToDto(message);
        StationState state = responseDto.getState();
        api.setState(state);
        log.debug("Station state was updated: {}", state);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected to station: " + reason + ", remote = " + remote);
        StationApi.getInstance().closeConnection();
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("On error: " + ex.getMessage());
        ex.printStackTrace();
    }

    public void sendCommand(StationCommand request) {
        System.out.println("send: " + request);
        request.setConversationToken(token);
        this.send(mapper.toBytes(request));
    }

    @Override
    public void send(String text) {
        System.out.println("Send to station: " + text);
        super.send(text);
    }
}
