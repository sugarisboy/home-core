package ru.sugarisboy.homecore.vk;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class VkScheduler {

    private final VkModuleState state;
    private final VkService vkService;

    /*@Scheduled(fixedDelay = 5000L)
    public void onSchedule() {
        Map<String, Object> response = vkService.pullActions();
        Long ts = (long) (int) response.get("ts");
        state.setTs(ts);

        List<?> updates = (List<?>) response.get("updates");
        log.info("Vk notify: {}", updates);

        vkService.resolveUpdate(updates);
    }*/
}
