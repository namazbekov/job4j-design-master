package ru.job4j.io;


import java.io.*;


public class ResultFile {
    public static void write(String filename, int[][]x) {
        File file = new File(filename);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < x.length; j++) {
                    String arr = Integer.toString(x[i][j]);
                    fileOutputStream.write(Byte.parseByte(arr + ""));

                    fileOutputStream.write(System.lineSeparator().getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int[][] array = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                array[i][j] = (i + 1) * (j + 1);
            }
        }

        ResultFile.write("result.txt", array);
    }
}