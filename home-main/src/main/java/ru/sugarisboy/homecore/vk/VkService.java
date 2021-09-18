package ru.sugarisboy.homecore.vk;

import java.util.List;
import java.util.Map;

public interface VkService {

    Map<String, Object> pullActions();

    void resolveUpdate(List<?> updates);

    void sendMessage(VkSendMessageDto sendMessageDto);
}
