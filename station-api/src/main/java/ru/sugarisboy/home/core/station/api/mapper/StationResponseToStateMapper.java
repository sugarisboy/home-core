package ru.sugarisboy.home.core.station.api.mapper;

import ru.sugarisboy.home.core.station.api.dto.StationClientResponse;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StationResponseToStateMapper {

    private final ObjectMapper mapper = objectMapper();

    public StationClientResponse convertToDto(String response) {
        try {
            return mapper.readValue(response, StationClientResponse.class);
        } catch (JsonProcessingException e) {
            log.warn("Can't read station response");
            throw new RuntimeException(e);
        }
    }

    public String toBytes(Object o) {
        try {
            return objectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                //.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                .enable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID)
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }
}
