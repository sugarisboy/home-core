package ru.sugarisboy.homecore.initializers;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tomcat.jni.Time;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import ru.sugarisboy.home.core.station.api.StationApi;
import ru.sugarisboy.homecore.configuration.StationProperties;

@Component
@ConditionalOnProperty(prefix = "home-core.station.api", name = "enabled", havingValue = "true")
@RequiredArgsConstructor
public class StationApiInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final StationProperties stationProperties;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        StationApi.getInstance().createConnect(stationProperties);
    }
}
