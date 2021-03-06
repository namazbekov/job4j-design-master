package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;
import java.io.*;
import java.util.*;

public class CSVReader {

    static StringBuilder stringBuilder = new StringBuilder();

    public static void handle(ArgsName argsName) {
        String[] filter = argsName.get("filter").split(",");
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        List<String> collectIndex = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            String firstRow = scanner.nextLine();
            List<String> list = List.of(firstRow.split(delimiter));
            for (String element : list) {
                for (String value : filter) {
                    if (element.equals(value)) {
                        collectIndex.add(String.valueOf(list.indexOf(value)));
                        stringBuilder.append(element).append(delimiter);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator());
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                List<String> list1 = List.of(nextLine.split(delimiter));
                for (int i = 0; i < list1.size(); i++) {
                    for (int j = 0; j < collectIndex.size(); j++) {
                        int element = Integer.parseInt(collectIndex.get(j));
                        if (i == element) {
                            stringBuilder.append(list1.get(i)).append(delimiter);
                        }
                    }
                }
                stringBuilder.append(System.lineSeparator());
            }

            if ("stdout".equals(out)) {
                System.out.println(stringBuilder);
            } else {
                File file = new File(out);
                try (FileWriter fileWriter = new FileWriter(file)) {
                        fileWriter.write(String.valueOf(stringBuilder));
                        fileWriter.write(System.lineSeparator());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void checkArguments(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        File file = new File(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        if (args.length != 4) {
            throw new IllegalArgumentException("number of arguments isn`t correct");
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("path of source isn`t correct");
        }
        if (!";".equals(delimiter)) {
            throw new IllegalArgumentException("delimiter isn`t correct");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        checkArguments(args);
        handle(ArgsName.of(args));

    }
}
