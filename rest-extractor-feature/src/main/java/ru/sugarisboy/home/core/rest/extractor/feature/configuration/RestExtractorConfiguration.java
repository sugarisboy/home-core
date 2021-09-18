package ru.sugarisboy.home.core.rest.extractor.feature.configuration;

import ru.sugarisboy.home.core.rest.extractor.feature.RestExtractorController;
import ru.sugarisboy.home.core.rest.extractor.feature.service.ExtractorService;

import org.springframework.context.annotation.Bean;

public class RestExtractorConfiguration {

    @Bean
    public ExtractorService extractorService() {
        return new ExtractorService();
    }

    @Bean
    public RestExtractorController restExtractorController(ExtractorService extractorService) {
        return new RestExtractorController(extractorService);
    }
}
