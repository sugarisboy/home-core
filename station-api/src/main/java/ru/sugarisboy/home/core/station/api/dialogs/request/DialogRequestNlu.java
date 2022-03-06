package ru.sugarisboy.home.core.station.api.dialogs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DialogRequestNlu {

    private List<String> tokens;
}
