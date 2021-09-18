package ru.sugarisboy.home.core.rest.extractor.feature;

import ru.sugarisboy.home.core.rest.extractor.feature.dto.ModuleApi;
import ru.sugarisboy.home.core.rest.extractor.feature.service.ExtractorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/extract")
@RequiredArgsConstructor
public class RestExtractorController {

    private final ExtractorService extractorService;

    @GetMapping("/state/{module}")
    public ResponseEntity<Object> extractModuleState(@PathVariable ModuleApi module) {
        Object state = extractorService.extract(module);
        return ResponseEntity.ok(state);
    }
}
