package ru.sugarisboy.homecore.vk;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.sugarisboy.homecore.api.ModuleState;
import javax.annotation.PostConstruct;

@Data
@Component
public class VkModuleState extends ModuleState {

    @PostConstruct
    public void init() {
        this.key = "761c2394d1e344006532405f494bcd33c9c244cb";
        this.ts = 1683979243L;
    }

    private String key;
    private Long ts;
}
