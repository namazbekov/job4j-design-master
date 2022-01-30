package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String elements;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            while ((elements = read.readLine()) != null) {
                String value = elements.trim();
                if (value.startsWith("#")) {
                    continue;
                }
                if (value.isEmpty()) {
                    continue;
                }
                if (value.contains("=") && !value.startsWith("=") && value.split("=").length == 2) {
                    String[] array = elements.split("=");
                    values.put(array[0], array[array.length - 1]);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        String element;
        if (values.containsKey(key)) {
            element = values.get(key);
        } else {
            throw new NoSuchElementException();
        }
        return element;
    }

    public static void main(String[] args) {
        Config config = new Config("./data/app.properties");
        config.load();
        System.out.println(config.value("hibernate.connection.password"));
    }

}