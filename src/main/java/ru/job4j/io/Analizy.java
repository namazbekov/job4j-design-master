package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public static void unavailable(String source) {
        String numberOne = null;
        String numberTwo = null;
        String read;
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            while ((read = in.readLine()) != null) {
                String[] array = read.split(" ");
                String first = array[0];
                String second = array[array.length - 1];
                if ("400".equals(first) || "500".equals(first)) {
                    numberOne = second;
                }
                if ("200".equals(first) || "300".equals(first)) {
                    numberTwo = second;
                }
            }
            System.out.println(numberOne + ";" + numberTwo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy.unavailable("c:\\projects\\job4j_design\\data\\server.log");
    }
}