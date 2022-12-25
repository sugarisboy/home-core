package ru.sugarisboy.homecore.configuration;

import ru.sugarisboy.home.core.station.api.dto.in.StationSocketInfo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("station")
public class StationProperties extends StationSocketInfo {

}
