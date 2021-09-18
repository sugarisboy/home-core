/*
package ru.sugarisboy.homecore.hearts;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.sugarisboy.homecore.station.PlayerState;
import ru.sugarisboy.homecore.station.PlayerStateExtra;
import ru.sugarisboy.homecore.station.StationService;
import ru.sugarisboy.homecore.station.StationState;
import ru.sugarisboy.home.core.common.api.utils.ImageUtils;
import ru.sugarisboy.homecore.utils.LampUtils;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BackgroundLampColorHeart {

    private static final int IMG_SIZE = 300;
    private static final int MIN_ALPHA = 40;

    private final StationStateonService stationService;

    private static Color lastColor = null;

    //@Async
    //@Scheduled(fixedDelay = 500L)
    public void backgroundLampColorHeart() {
        StationState state = stationService.getState();
        String imageUrl = Optional.ofNullable(state)
                .map(StationState::getPlayerState)
                .map(PlayerState::getExtra)
                .map(PlayerStateExtra::getCoverUri)
                .orElse(null);

        if (imageUrl == null) {
            return;
        }

        String sizeableImageBySize = ImageUtils.getSizeableImageBySizeUrl(imageUrl, IMG_SIZE);
        BufferedImage bufferedImage = ImageUtils.readImageByUrl(sizeableImageBySize).orElse(null);

        if (bufferedImage == null) {
            return;
        }

        Color averageColor = ImageUtils.getAverageColor(bufferedImage, MIN_ALPHA);
        if (averageColor != null && averageColor.equals(lastColor)) {
            return;
        }
        lastColor = averageColor;

        List.of(
                "192.168.1.192",
                "192.168.1.113"
        ).forEach(ip -> LampUtils.setColor(ip, averageColor));
    }
}
*/
