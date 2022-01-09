package ru.job4j.io;


import java.io.*;


public class ResultFile {
    public static void write(String filename, int[][]x) throws IOException {
        BufferedWriter outputWriter;
        outputWriter = new BufferedWriter(new FileWriter(filename));
        for (int[] ints : x) {
            for (int j = 0; j < x.length; j++) {
                outputWriter.write(ints[j] + "");

                outputWriter.newLine();
            }
        }
        outputWriter.flush();
        outputWriter.close();
    }

    public static void main(String[] args) throws IOException {
        int[][] array = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                array[i][j] = (i + 1) * (j + 1);
            }
        }

        ResultFile.write("result.txt", array);
    }
}