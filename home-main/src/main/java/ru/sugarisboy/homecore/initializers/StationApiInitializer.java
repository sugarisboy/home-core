package ru.sugarisboy.homecore.initializers;

import lombok.SneakyThrows;
import org.apache.tomcat.jni.Time;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import ru.sugarisboy.home.core.station.api.StationApi;

@Component
@ConditionalOnProperty(prefix = "home-core.station.api", name = "enabled", havingValue = "true")
public class StationApiInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        StationApi.getInstance().createConnect();
    }
}
