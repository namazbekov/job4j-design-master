package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

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
                String[] array = elements.split("=");
                String first = array[0];
                String second = array[array.length - 1];
                if (!first.contains("#")) {
                    values.put(first, second);
                }
            }
            System.out.println(values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        String value = null;
        for (Map.Entry<String, String> var : values.entrySet()) {
            if (var.getKey().contains(key)) {
                value = var.getValue();
            }
        }
        return value;
    }

    public static void main(String[] args) {
        Config config = new Config("app.properties");
        config.load();
        System.out.println(config.value("hibernate.connection.password"));
    }

}