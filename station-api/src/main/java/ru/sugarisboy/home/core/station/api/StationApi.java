package ru.sugarisboy.home.core.station.api;

import ru.sugarisboy.home.core.station.api.dto.in.StationCommand;
import ru.sugarisboy.home.core.station.api.dto.in.StationRequestPayloadBuilder;
import ru.sugarisboy.home.core.station.api.dto.in.StationSocketInfo;
import ru.sugarisboy.home.core.station.api.dto.out.StationState;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.val;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class StationApi {

    public static void main(String[] args) throws InterruptedException {

        val wsAddress = "wss://192.168.1.96:1961/";
        val caCert = "-----BEGIN CERTIFICATE-----\nMIIC3TCCAcWgAwIBAgIBATANBgkqhkiG9w0BAQsFADAyMQswCQYDVQQGEwJSVTES\nMBAGA1UEAwwJbG9jYWxob3N0MQ8wDQYDVQQKDAZZYW5kZXgwHhcNMjAxMDMxMjE1\nNjI0WhcNMjMxMDMxMjE1NjI0WjAyMQswCQYDVQQGEwJSVTESMBAGA1UEAwwJbG9j\nYWxob3N0MQ8wDQYDVQQKDAZZYW5kZXgwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAw\nggEKAoIBAQDe/B5eNnc73ZzDLIYylIDXoxpEXX01C2dM7K2ovbkbFAxYmLtdPBS7\nBo7nhJNIO6EgBxedvjuvW2Djidhq34U2/svBzCeWa4rQUO8vKh05S0p9sRg2o6v5\n2dfN9bKEKUvVPkwMqTd0URRXUCec4vWxz1GuEjzZbZ0jSaiKYU1mSLnk+lMyYxDy\n6+fuhhaisDX1R3ApOCDwZwPuUMYWxlcoSeGJdRlo0koKb5xX/tFbvfA7W7K8XanL\nNWt6Y4r6xugLquOUWtduOtbwumm/ngIYJQIynlA4pjBybfTQN9aHrLHDszhFIxIO\nReUuGkiBI7ITSi0LiU5VXkpZpG0skZk1AgMBAAEwDQYJKoZIhvcNAQELBQADggEB\nAGBUXGruyV5QWG5P5HUV3PLJZUpDeJ5/h89c5sKiRaAk7i2UY2+9cqVSymRgtDOI\nwLJkbr3QGKAz40wJ3aACHZzFFCei7/6GmuRFdx9ThHq7GKQvHrtEnmFVDnK39Ev/\nFcR1A+s/iPdBTJxltCerAbfjYq8e/vPatdn1D3CJ2Y+q4B3vN17UlqsFX8o9g8ln\nx089F+HIznOUERSw1tH2yG4pjUiLvHnotnM1pQmQ8eyWdSnuCbMSNDX2wPaYFBry\nY/Q0VPO5lNVzPiIKdVtRfmj8JfXjKOm52AqYCKjDFZGCbnKrcekU6o48O2kpwGLF\nTz2mEWnoFTFNy2vk3Oi7mSo=\n-----END CERTIFICATE-----\n";
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJnbGFnb2wiLCJleHAiOjE2NDc3ODMwMTAsImlzcyI6InF1YXNhci1iYWNrZW5kIiwicGx0IjoieWFuZGV4c3RhdGlvbiIsInN1YiI6IjU0MjA3ODk2ODMwODA4MGYwMzEwIn0.Zw87La45WNjqa2B1az0XS0ijN5gTkP8j4YvqnRe_5-Q";

        val socketInfo = new StationSocketInfo(caCert, token, wsAddress);

        StationApi.getInstance().createConnect(socketInfo);

        val state = StationApi.getInstance().getState();

        //StationApi.getInstance().command().repeatText("Привет медвед").send();

        StationApi.getInstance().command()
                .prevTrack()
                .onError(e -> System.out.println("Не удалось переключить на предыдущий трек"))
                .send();

        /*while (StationApi.getInstance().isConnected && OffsetDateTime.now().toEpochSecond() != 0) {
            Thread.sleep(500L);
            System.out.println(StationApi.getInstance().isConnected);
            System.out.println(StationApi.getInstance().getState());
        }*/
        System.out.println("1" + StationApi.getInstance().isConnected());
        Thread.sleep(15000L);
        System.out.println("2" + StationApi.getInstance().isConnected());
        StationApi.getInstance().command()
                .prevTrack()
                .onError(e -> System.out.println("Не удалось переключить на предыдущий трек"))
                .send();
        System.out.println("3" + StationApi.getInstance().isConnected());

        System.out.println("4" + StationApi.getInstance().isConnected());
        Thread.sleep(650000L);
        System.out.println("5" + StationApi.getInstance().isConnected());
    }

    private static StationWsConnection wsConnection;

    private boolean isConnected;

    @Setter
    private StationState state;

    public void createConnect(StationSocketInfo stationSocketInfo) {
        try {
            if (!isConnected) {
                wsConnection = new StationWebSocketClient(stationSocketInfo).createConnection();
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
