package ru.sugarisboy.homecore.vk;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@FeignClient(
        name = "vk-ts-api",
        url = "${integrations.vk.ts-api}"
)
public interface VkTsApiClient {

    @GetMapping("/{nim}")
    Map<String, Object> pullActions(@PathVariable String nim,
                                    @RequestParam VkAct act,
                                    @RequestParam String key,
                                    @RequestParam String mode,
                                    @RequestParam long ts,
                                    @RequestParam int version,
                                    @RequestParam int wait);
}
