package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    private boolean checkElements(String[] args) {
        return args[1].isEmpty();
    }

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("no such key");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Array don`t have elements");
        }
        for (String array : args) {
            if (!array.startsWith("-")) {
                throw new IllegalArgumentException("no such - ");
            }
            String element = array.substring(1);
            String[] arrayArgs = element.split("=", 2);
            if (checkElements(arrayArgs)) {
                throw new IllegalArgumentException("Key don`t have value");
            }
            values.put(arrayArgs[0], arrayArgs[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}