package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();
    private final Properties properties = new Properties();

    public Config(final String path) {
        this.path = path;
    }
// загружает файл в properties
    public void load() {
        try {
            properties.load(new BufferedReader(new FileReader(this.path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
// метод value() возвращает значение ключа
    public String value(String key) {
        return properties.getProperty(key);
    }
}
