package ru.sugarisboy.home.core.rest.extractor.feature.controller;

import ru.sugarisboy.home.core.rest.extractor.feature.service.DialogService;
import ru.sugarisboy.home.core.station.api.dialogs.request.DialogInputDto;
import ru.sugarisboy.home.core.station.api.dialogs.response.DialogOutputDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/dialog")
@RequiredArgsConstructor
public class DialogController {

    private final DialogService dialogService;

    @GetMapping("/v1.0")
    public ResponseEntity<?> ping() {
        log.debug("Dialog pinging");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{urlPath}")
    public DialogOutputDto dialog(@PathVariable String urlPath, @RequestBody DialogInputDto request) {
        return dialogService.resolve(urlPath, request);
    }
}
