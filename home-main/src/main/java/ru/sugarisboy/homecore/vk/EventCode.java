package ru.sugarisboy.homecore.vk;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EventCode {
    MESSAGE(4);

    @Getter
    private final int code;
}
