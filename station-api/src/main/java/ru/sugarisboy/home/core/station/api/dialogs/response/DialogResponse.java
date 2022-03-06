package ru.sugarisboy.home.core.station.api.dialogs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DialogResponse {

    private String text;

    @JsonProperty("tts")
    private String speech;

    @JsonProperty("end_session")
    private boolean endSession;
}
