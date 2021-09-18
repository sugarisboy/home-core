package ru.sugarisboy.home.core.common.api.utils;

import lombok.experimental.UtilityClass;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import javax.imageio.ImageIO;

@UtilityClass
public class ImageUtils {

    public String getSizeableImageBySizeUrl(String url, int px) {
        return "https://" + url.replace("%%", String.format("%dx%d", px, px));
    }

    public Optional<BufferedImage> readImageByUrl(String url) {
        try {
            URL URL = new URL(url);
            return Optional.ofNullable(ImageIO.read(URL));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Color getAverageColor(BufferedImage img, int minAlpha) {
        int width = img.getWidth();
        int height = img.getHeight();
        long sumR = 0, sumG = 0, sumB = 0, count = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color pixel = new Color(img.getRGB(x, y));

                int alpha = pixel.getAlpha();
                if (alpha < minAlpha || pixel.getRed() + pixel.getGreen() + pixel.getBlue() > 720) {
                    continue;
                }
                sumR += pixel.getRed();
                sumG += pixel.getGreen();
                sumB += pixel.getBlue();
                count++;
            }
        }

        if (count == 0) {
            return Color.ORANGE;
        }

        int avgR = (int) (sumR / count);
        int avgG = (int) (sumG / count);
        int avgB = (int) (sumB / count);

        return new Color(avgR, avgG, avgB);
    }
}
