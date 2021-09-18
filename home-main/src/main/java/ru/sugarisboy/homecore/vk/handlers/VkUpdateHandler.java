package ru.sugarisboy.homecore.vk.handlers;

import lombok.RequiredArgsConstructor;
import ru.sugarisboy.homecore.vk.EventCode;
import java.util.ArrayList;
import java.util.List;

public abstract class VkUpdateHandler {

    public abstract EventCode getEventCode();

    public abstract void onHandle(List<?> eventBody);
}
