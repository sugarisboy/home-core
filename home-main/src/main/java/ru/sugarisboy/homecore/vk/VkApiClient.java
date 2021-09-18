package ru.sugarisboy.homecore.vk;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "vk-api",
        url = "${integrations.vk.api}"
)
public interface VkApiClient {

    String COOKIE = "" +
            "remixlang=0; " +
            "remixstid=256761197_PDpZMvryK81ZFFWlzUpGPGLb9LDOFPh6L7zb6jPGMto; " +
            "remixflash=0.0.0; " +
            "remixscreen_width=1900; " +
            "remixscreen_height=1080; " +
            "remixscreen_dpr=1; " +
            "remixscreen_depth=24; " +
            "remixdt=0; " +
            "remixttpid=bfbd8763ce4d0d4321905cf562e68bcd03e0e7a251; " +
            "remixuas=NDdjZjg2OWFkNGJiNDA4YjI0MjczNjY1; " +
            "remixua=41%7C-1%7C104%7C1823740339; " +
            "remixseenads=1; " +
            "remixscreen_winzoom=1; " +
            "remixsid=350f83599322b3ee648ed3f368685654e805893e0150608185d727004924d; " +
            "remixscreen_orient=1; " +
            "remixgp=bfa5461c81061cd6175a8637410f60ed; " +
            "remixrefkey=6ceb889ee3d23c00da";

    @Headers("Cookie: " + COOKIE)
    @PostMapping(value = "/al_im.php", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    String sendMessage(@RequestParam VkAct act,
                       @RequestHeader("Cookie") String cookie,
                       @RequestBody VkSendMessageDto body);
}
