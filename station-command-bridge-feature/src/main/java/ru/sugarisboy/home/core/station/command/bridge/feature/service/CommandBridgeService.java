package ru.sugarisboy.home.core.station.command.bridge.feature.service;

public interface CommandBridgeService {
    void stop();

    void play();

    void nextTrack();

    void prevTrack();

    void likeCurrentTrack();

    void unlikeCurrentTrack();

    void setVolume(float value);
}
