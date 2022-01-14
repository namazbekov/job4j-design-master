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
            String[] string = list.toArray(new String[0]);
            for (String value : string) {
                String[] last = value.split(" ");
                if (last[last.length - 2].equals("404")) {
                    result.add(Arrays.toString(last));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}