package ru.sugarisboy.home.core.station.api.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationSocketInfo {

    private String caCert;
    private String token;
    private String wsAddress;
}
