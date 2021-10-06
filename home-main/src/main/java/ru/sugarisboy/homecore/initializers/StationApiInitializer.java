package ru.sugarisboy.homecore.initializers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import ru.sugarisboy.home.core.station.api.StationApi;

@ConditionalOnProperty(prefix = "home-core.station.api", name = "enabled", havingValue = "true")
public class StationApiInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        StationApi.getInstance().createConnect();
    }
}
