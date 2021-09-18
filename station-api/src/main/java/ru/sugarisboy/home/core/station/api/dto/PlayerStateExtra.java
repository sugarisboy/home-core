package ru.sugarisboy.home.core.station.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStateExtra {

    @JsonProperty("coverURI")
    private String coverUri;

    private StationStateType stateType;
}
