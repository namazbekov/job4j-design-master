package ru.job4j.io;

import ru.job4j.list.SimpleArrayList;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogFilter {
    public static List<String> filter(String file) {
        StringBuilder text = new StringBuilder();
        List<String> list = new ArrayList<>();
        String elements;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            while ((elements = in.readLine()) != null) {
                text.append(elements);
            }
            System.out.println(text);
            String[] token = text.toString().split(" ");
            for (String line : token) {
                if ("404".equals(line)) {
                    list.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}