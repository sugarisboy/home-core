package ru.sugarisboy.homecore.vk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.concurrent.ThreadLocalRandom;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VkSendMessageDto {

    public VkSendMessageDto(long receiver, String msg, String hash) {
        this.to = receiver;
        this.msg = msg;
        this.hash = hash;
    }

    private long to;
    private String msg;
    private String hash;
    private VkAct act = VkAct.a_send;

    private String al = "1";
    private String entrypoint = "list_all";
    private String im_v = "3";
    private String module = "im";

    private int random_id = 1272421123 + ThreadLocalRandom.current().nextInt(10000000);

    private long guid = 10000000000000000L +  ThreadLocalRandom.current().nextLong(6277514901959170L);
}
