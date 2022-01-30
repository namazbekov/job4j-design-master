package ru.job4j.io;

import java.io.*;

public class Analizy {

    public static void unavailable(File source, File target) {
        String read;
        StringBuilder stringBuilder = new StringBuilder();
        boolean isWorks = true;
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            while ((read = in.readLine()) != null) {
                String[] array = read.split(" ");

                if ("400".equals(array[0]) || "500".equals(array[0]) && isWorks) {
                    stringBuilder.append(array[1]).append(";");
                    isWorks = false;
                }

                if ("300".equals(array[0]) || "200".equals(array[0]) && !isWorks) {
                    stringBuilder.append(array[1]).append(";");
                    isWorks = true;
                    stringBuilder.append(System.lineSeparator());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(target)) {
            out.println(stringBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy.unavailable(new File("./data/server.log"), new File("./data/result.server.log"));
    }
}