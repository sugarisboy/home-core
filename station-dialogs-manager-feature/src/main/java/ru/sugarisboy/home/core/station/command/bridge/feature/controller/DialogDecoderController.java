package ru.sugarisboy.home.core.station.command.bridge.feature.controller;

import ru.sugarisboy.home.core.station.api.DialogManagerApi;
import ru.sugarisboy.home.core.station.api.dialogs.usage.CommandDecoder;
import ru.sugarisboy.home.core.station.command.bridge.feature.wrapper.TimerVoiceWrapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Set;
import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DialogDecoderController {

    private final TimerVoiceWrapper timerVoiceWrapper;

    @PostConstruct
    public void init() {
        Set.of(
                timerDecoder()
        )
                .forEach(decoder -> DialogManagerApi.getInstance().addDecoder(decoder));
    }

    private CommandDecoder timerDecoder() {
        return CommandDecoder.builder()
                .urlPath("main")
                .addCommand()
                    .addPhrase()
                        .startWith("Засеки")
                        .startWith("Засечь")
                        .end()
                    .then(timerVoiceWrapper::startTimer)
                .addCommand()
                    .addPhrase()
                        .startWith("какие")
                        .startWith("какой")
                        .startWith("список")
                        .contains("таймер")
                        .end()
                    .addPhrase()
                        .startWith("сколько до конца")
                        .contains("таймер")
                        .end()
                    .then(timerVoiceWrapper::listTimers)
                .orElseThrow(input -> new RuntimeException("Not resolve: " + input.getRequest()));
    }
}
