package ru.sugarisboy.home.core.rest.extractor.feature.service;

import ru.sugarisboy.home.core.rest.extractor.feature.dto.ModuleApi;
import ru.sugarisboy.home.core.station.api.StationApi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExtractorService {

    private final StationApi stationApi = StationApi.getInstance();

    public Object extract(ModuleApi module) {
        if (module.equals(ModuleApi.STATION)) {
            return stationApi.getState();
        }

        return null;
    }
}
