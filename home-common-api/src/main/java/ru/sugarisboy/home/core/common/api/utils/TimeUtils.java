package ru.sugarisboy.home.core.common.api.utils;

import lombok.experimental.UtilityClass;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class TimeUtils {

    public ChronoUnit convertToChronoUnit(String s) {
        s = s.toLowerCase();
        if (s.startsWith("сек")) {
            return ChronoUnit.SECONDS;
        } else if (s.startsWith("мин")) {
            return ChronoUnit.MINUTES;
        } else if (s.startsWith("час")) {
            return ChronoUnit.HOURS;
        }

        throw new RuntimeException("Не удалось сконвертировать [" + s + "] в временную единицу");
    }
}
