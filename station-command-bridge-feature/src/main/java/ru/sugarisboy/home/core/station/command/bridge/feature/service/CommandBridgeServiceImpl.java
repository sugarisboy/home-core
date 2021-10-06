package ru.sugarisboy.home.core.station.command.bridge.feature.service;

import ru.sugarisboy.home.core.station.api.StationApi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sugarisboy.home.core.station.api.dto.StationCommand;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommandBridgeServiceImpl implements CommandBridgeService {

    private final StationApi station = StationApi.getInstance();

    @Override
    public void stop() {
        station.send(StationCommand.);
    }

    @Override
    public void play() {

    }

    @Override
    public void nextTrack() {

    }

    @Override
    public void prevTrack() {

    }

    @Override
    public void likeCurrentTrack() {

    }

    @Override
    public void unlikeCurrentTrack() {

    }

    @Override
    public void setVolume(float value) {

    }
}
