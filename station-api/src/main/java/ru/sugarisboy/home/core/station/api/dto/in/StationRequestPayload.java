package ru.sugarisboy.home.core.station.api.dto.in;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.Accessors;

@Getter
@Setter(value = AccessLevel.PACKAGE)
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StationRequestPayload {

    private String command;
    private Integer position;
    private String type;
    private String id;
    private Float volume;
    private String text;

    public static StationRequestPayloadBuilder builder(StationCommand.Builder parentBuilder) {
        return new StationRequestPayloadBuilder(parentBuilder);
    }
}
