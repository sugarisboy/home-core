package ru.sugarisboy.home.core.station.command.bridge.feature.service;

import ru.sugarisboy.home.core.station.api.StationApi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommandBridgeServiceImpl implements CommandBridgeService {

    private final StationApi station = StationApi.getInstance();

    @Override
    public void stop() {
        station.command().stop().send();
    }

    @Override
    public void play() {
        station.command().play().send();
    }

    @Override
    public void nextTrack() {
        station.command().nextTrack().send();
    }

    @Override
    public void prevTrack() {
        station.command().prevTrack().send();
    }

    @Override
    public void likeCurrentTrack() {
        station.command().like().send();
    }

    @Override
    public void unlikeCurrentTrack() {
        station.command().unlike().send();
    }

    @Override
    public void setVolume(float value) {
        station.command().changeVolume(value).send();
    }
}
