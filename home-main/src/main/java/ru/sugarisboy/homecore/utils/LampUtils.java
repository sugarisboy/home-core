/*
package ru.sugarisboy.homecore.utils;

import com.mollin.yapi.YeelightDevice;
import com.mollin.yapi.enumeration.YeelightEffect;
import com.mollin.yapi.exception.YeelightResultErrorException;
import com.mollin.yapi.exception.YeelightSocketException;
import lombok.experimental.UtilityClass;
import java.awt.*;

@UtilityClass
public class LampUtils {

    public void setColor(String ip, Color color) {
        try {
            YeelightDevice device = new YeelightDevice(ip, 55443, YeelightEffect.SMOOTH, 500);
            device.setRGB(color.getRed(), color.getGreen(), color.getBlue());
        } catch (YeelightSocketException | YeelightResultErrorException e) {
            e.printStackTrace();
        }
    }
}
*/
