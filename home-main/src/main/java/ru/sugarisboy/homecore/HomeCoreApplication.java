package ru.sugarisboy.homecore;

import static ru.sugarisboy.homecore.configuration.ApiConstant.HOME_MAIN_PACKAGE;

import ru.sugarisboy.home.core.station.api.StationApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(scanBasePackages = HOME_MAIN_PACKAGE)
public class HomeCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeCoreApplication.class, args);
    }

    @EventListener(classes = {ApplicationReadyEvent.class})
    public void stop() {
		StationApi.getInstance().connect();
    }
}
