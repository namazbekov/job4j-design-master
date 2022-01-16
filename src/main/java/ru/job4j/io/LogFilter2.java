package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter2 {

    public static List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        String elements;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            while ((elements = in.readLine()) != null) {
                String[] last = elements.split(" ");
                if ("404".equals(last[last.length - 2])) {
                    result.add(elements);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String value : log) {
                out.printf("%s%n", value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}
