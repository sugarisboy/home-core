package ru.sugarisboy.home.core.station.timers.feature.service;

import ru.sugarisboy.home.core.station.timers.feature.model.Timer;
import ru.sugarisboy.home.core.station.timers.feature.repository.TimerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TimerService {

    private final TimerRepository timerRepository;

    @Transactional
    public Timer startTimer(OffsetDateTime endTime, String name) {
        Timer timer = new Timer();
        timer.setStart(OffsetDateTime.now());
        timer.setFinish(endTime);
        timer.setName(name);

        return timerRepository.save(timer);
    }

    public List<Timer> findActive() {
        return timerRepository.findFinishBefore(OffsetDateTime.now());
    }
}
