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
            int[] numbers = new int[6];
            int counter = 0;
            for (String line : lines) {
                numbers[counter++] = Integer.parseInt(line);
            }
            for (int element : numbers) {
                if (element % 2 == 0) {
                    System.out.println(element + " - четное число");
                }
            }
            System.out.println(Arrays.toString(numbers));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
