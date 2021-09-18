package ru.sugarisboy.homecore.vk;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sugarisboy.homecore.vk.handlers.VkUpdateHandler;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class VkServiceImpl implements VkService {

    private final VkApiClient vkApiClient;
    private final VkTsApiClient vkTsApiClient;
    private final VkModuleState state;
    private final Set<VkUpdateHandler> updateHandlers;

    @Override
    public Map<String, Object> pullActions() {
        Optional.ofNullable(state.getKey()).orElseThrow(
                () -> new IllegalArgumentException("Key is null in state"));
        Optional.ofNullable(state.getTs()).orElseThrow(
                () -> new IllegalArgumentException("Ts is null in state"));

        return vkTsApiClient.pullActions(
                VkApiConstants.NIM,
                VkAct.a_check,
                state.getKey(),
                VkApiConstants.MODE,
                state.getTs(),
                VkApiConstants.VER,
                VkApiConstants.WAIT
        );
    }

    @Override
    public void resolveUpdate(List<?> updates) {
        updates.stream()
                .filter(update -> update instanceof List)
                .map(update -> (List<?>) update)
                .forEach(update -> {
                    int code = (int) update.get(0);
                    updateHandlers.stream()
                            .filter(handler -> handler.getEventCode().getCode() == code)
                            .findFirst()
                            .ifPresent(handler -> handler.onHandle(update));
                });
    }

    @Override
    public void sendMessage(VkSendMessageDto sendMessageDto) {
        vkApiClient.sendMessage(VkAct.a_send, VkApiClient.COOKIE, sendMessageDto);
    }
}
