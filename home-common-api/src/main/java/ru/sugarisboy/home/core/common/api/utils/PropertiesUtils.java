package ru.sugarisboy.home.core.common.api.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;

@UtilityClass
public class PropertiesUtils {

    @SneakyThrows
    public Properties read(String fileName) {
        Properties prop = new Properties();

        URI uri = PropertiesUtils.class.getClassLoader().getResource(fileName).toURI();
        try (FileInputStream fis = new FileInputStream(new File(uri))) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }
}
