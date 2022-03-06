package ru.sugarisboy.home.core.station.command.bridge.feature.controller;

import org.springframework.web.bind.annotation.*;
import ru.sugarisboy.home.core.station.command.bridge.feature.service.CommandBridgeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/station/execute")
public class CommandBridgeController {

    private final CommandBridgeService commandBridgeService;

    @PostMapping("/stop")
    public void executeStop() {
        commandBridgeService.stop();
    }

    @PostMapping("/play")
    public void executePlay() {
        commandBridgeService.play();
    }

    @PostMapping("/next")
    public void executeNextTrack() {
        commandBridgeService.nextTrack();
    }

    @PostMapping("/prev")
    public void executePrevTrack() {
        commandBridgeService.prevTrack();
    }

    @PostMapping("/like")
    public void executeLike() {
        commandBridgeService.likeCurrentTrack();
    }

    @PostMapping("/unlike")
    public void executeUnlike() {
        commandBridgeService.unlikeCurrentTrack();
    }

    @PutMapping("/volume")
    public void setVolume(@RequestParam float value) {
        commandBridgeService.setVolume(value);
    }
}
