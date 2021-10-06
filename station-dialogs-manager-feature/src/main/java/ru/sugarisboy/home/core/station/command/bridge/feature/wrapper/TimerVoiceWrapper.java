package ru.sugarisboy.home.core.station.command.bridge.feature.wrapper;

import ru.sugarisboy.home.core.common.api.utils.StringUtils;
import ru.sugarisboy.home.core.common.api.utils.TimeUtils;
import ru.sugarisboy.home.core.station.api.dialogs.request.DialogRequest;
import ru.sugarisboy.home.core.station.api.dialogs.usage.CommandResponse;
import ru.sugarisboy.home.core.station.api.dialogs.usage.StationAudio;
import ru.sugarisboy.home.core.station.api.dialogs.usage.TtsMessage;
import ru.sugarisboy.home.core.station.command.bridge.feature.model.Timer;
import ru.sugarisboy.home.core.station.command.bridge.feature.service.TimerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimerVoiceWrapper {

    private final TimerService timerService;

    public CommandResponse startTimer(DialogRequest request) {
        log.debug("Start timer with words: {}", request);

        val cmd = request.getCommand();
        val rawDateTime = StringUtils.extractByRegEx(cmd, "\\d+\\s(мин|сек|час)")
                .orElseThrow(() -> new RuntimeException("Не удалось извлечь время таймера"));

        val split = rawDateTime.split("\\s");
        val count = Long.parseLong(split[0]);
        val timeUnit = TimeUtils.convertToChronoUnit(split[1]);

        val now = OffsetDateTime.now();
        val endTime = now.plus(count, timeUnit);

        val timer = timerService.startTimer(endTime, null);
        val previewText = String.format("Таймер был установлен на %d:%d", endTime.getHour(), endTime.getMinute());

        val seconds = ChronoUnit.SECONDS.between(now, endTime);
        val speech = String.format("Таймер запущен на %d секунд, время пошло!", seconds);

        return CommandResponse.builder()
                .previewText(previewText)
                .speech()
                .add(speech)
                .addAudio(StationAudio.THINGS_EXPLOSION)
                .speechEnd()
                .endSession()
                .build();
    }

    public CommandResponse listTimers(DialogRequest request) {
        List<Timer> active = timerService.findActive();

        String previewText;
        TtsMessage.Builder speech = TtsMessage.builder();

        if (active.isEmpty()) {
            previewText = "Нет запущенных таймеров";
            speech.add(previewText);
        } else if (active.size() == 1) {
            Timer timer = active.get(0);
            previewText = String.format("Запущен таймер до %d:%d, осталось %d минут %d секунд",
                    timer.getFinish().getHour(),
                    timer.getFinish().getMinute(),
                    ChronoUnit.MINUTES.between(timer.getStart(), timer.getFinish()),
                    ChronoUnit.SECONDS.between(timer.getStart(), timer.getFinish())
            );
            speech.add(previewText);
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append("Запущено несколько таймеров. ");
            speech.add("Запущено несколько таймеров. ");
            int index = 1;
            for (Timer timer : active) {
                builder.append(String.format("\n%d. Таймер до %d:%d, осталось %d минут %d секунд",
                        index,
                        timer.getFinish().getHour(),
                        timer.getFinish().getMinute(),
                        ChronoUnit.MINUTES.between(OffsetDateTime.now(), timer.getFinish()),
                        ChronoUnit.SECONDS.between(OffsetDateTime.now(), timer.getFinish()) % 60
                ));
                speech.add(String.format("Таймер номер %d, до %d часов %d минут, осталось %d минут %d секунд",
                        index,
                        timer.getFinish().getHour(),
                        timer.getFinish().getMinute(),
                        ChronoUnit.MINUTES.between(OffsetDateTime.now(), timer.getFinish()),
                        ChronoUnit.SECONDS.between(OffsetDateTime.now(), timer.getFinish()) % 60
                ));
                speech.addPause(200);
                index++;
            }
            previewText = builder.toString();
        }

        return CommandResponse.builder()
                .previewText(previewText)
                .speech(speech.asString())
                .endSession()
                .build();
    }
}
