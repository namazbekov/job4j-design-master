package ru.job4j.io;

import java.io.FileInputStream;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(" ");
            int numbers;
            for (String line : lines) {
                numbers = Integer.parseInt(line);
                if (numbers % 2 == 0) {
                    System.out.println(numbers);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
