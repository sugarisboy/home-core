package ru.sugarisboy.homecore.vk.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.sugarisboy.homecore.station.StationService;
import ru.sugarisboy.homecore.vk.EventCode;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class VkMessageHandler extends VkUpdateHandler {

    private final StationService stationService;

    @Override
    public EventCode getEventCode() {
        return EventCode.MESSAGE;
    }

    @Override
    public void onHandle(List<?> eventBody) {
        log.debug("body = {}; msg = {}", eventBody, eventBody.get(6));

        String message = (String) eventBody.get(6);
        stationService.sendSpeak("Вам новое сообщение: " + message);
    }
}
