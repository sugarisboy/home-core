/*
package ru.sugarisboy.homecore.hearts;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.sugarisboy.homecore.station.StationService;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class WelcomeInHomeHeart {

    private final static String IP_NIKITA = "192.168.1.195";
    private final static String IP_NATASHA = "192.168.1.29";
    private final static int TIMEOUT_DELAY = 10000;
    private final static Map<String, Boolean> prevValues = Collections.synchronizedMap(new HashMap<>());

    private final StationService stationService;

    //@Scheduled(fixedDelay = TIMEOUT_DELAY)
    public void welcomeInHomeNikitaHeart() throws IOException {
        listen(
                () -> hello("Пацючонок"),
                () -> goodbye("Пац"),
                IP_NIKITA
        );
    }

    //@Scheduled(fixedDelay = TIMEOUT_DELAY)
    public void welcomeInHomeNatashaHeart() throws IOException {
        listen(
                () -> hello("Наталья"),
                () -> goodbye("Натах"),
                IP_NATASHA
        );
    }

    private void hello(String name) {
        String msg = String.format("%s, Я скучала по вам. Надеюсь вы меня больше не будете бросать.", name);
        stationService.sendSpeak(msg);
    }

    private void goodbye(String name) {
        String msg = String.format("%s, куда же ты? Надеюсь до магазина и обратно!", name);
        stationService.sendSpeak(msg);
    }

    private void listen(Runnable connectAction, Runnable disconnectAction, String ip) {
        if (!prevValues.containsKey(ip)) {
            prevValues.put(ip, isConnected(ip));
        } else {
            Boolean prev = prevValues.get(ip);
            boolean updated = isConnected(ip);
            if (!prev.equals(updated)) {
                if (updated) {
                    System.out.println(ip + " - connected");
                    connectAction.run();
                } else {
                    System.out.println(ip + " - disconnected");
                    disconnectAction.run();
                }
                prevValues.put(ip, updated);
            }
        }
    }

    private boolean isConnected(String ip) {
        try {
            return InetAddress.getByName(ip).isReachable(TIMEOUT_DELAY / 2);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
*/
