package ru.sugarisboy.home.core.station.api.dialogs.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DialogOutputDto {

    private DialogResponse response;

    private String version = "1.0";
}
