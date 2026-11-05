package com.example.qa.config;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

public class Config {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input =
                     Config.class.getResourceAsStream("/config.properties")) {

            if (input == null) {
                throw new IllegalStateException("Не найден config.properties");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new IllegalStateException("Не удалось загрузить настройки", e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);

        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Не задана настройка: " + key
            );
        }

        return value;
    }

}
