package ru.job4j.io;

import java.io.*;

public class ResultFile2 {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("result.txt")))) {
            out.println("Hello World!");
            out.println("Hello");
            out.printf("%s%n", "Something");
            out.printf("%d%n", 10);
            out.printf("%f%n", 10f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
