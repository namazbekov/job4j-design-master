package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() throws IllegalArgumentException, NoSuchElementException {
        String elements;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            while ((elements = read.readLine()) != null) {
                if (elements.startsWith("#")) {
                    continue;
                }
                if (elements.split("=").length != 2) {
                    throw new IllegalArgumentException();
                }
                if (!elements.contains("=")) {
                    throw new NoSuchElementException();
                }
                String[] array = elements.split("=");
                if (" ".equals(array[0]) || " ".equals(array[array.length - 1])) {
                    throw new IllegalArgumentException();
                }
                values.put(array[0], array[array.length - 1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) throws NoSuchElementException {
        String element = null;
        if (values.containsKey(key)) {
            element = values.get(key);
        }
        return element;
    }

    public static void main(String[] args) {
        Config config = new Config("./data/app.properties");
        config.load();
        System.out.println(config.value("hibernate.connection.password"));
    }

}