package ru.job4j.io;

import com.sun.jdi.request.StepRequest;
import ru.job4j.list.SimpleArrayList;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        String elements;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            while ((elements = in.readLine()) != null) {
                list.add(elements);
            }
            for (String value : list) {
                String[] last = value.split(" ");
                if ("404".equals(last[last.length - 2])) {
                    result.add(value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        log.forEach(System.out::println);
    }
}