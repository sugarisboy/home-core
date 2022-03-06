package ru.sugarisboy.home.core.station.api.dialogs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DialogRequest {

    private DialogRequestType type;

    private String command;

    @JsonProperty("original_utterance")
    private String originalCommand;

    @JsonProperty("nlu")
    private DialogRequestNlu entities;
}
