package ru.sugarisboy.home.core.station.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationClientResponse {

    private StationState state;
}