package ru.job4j.io.scanner;

import com.sun.jdi.request.StepRequest;
import ru.job4j.io.ArgsName;
import java.io.*;
import java.util.*;

public class CSVReader {

    static List<String> result = new ArrayList<>();

    public static void handle(ArgsName argsName) {
        String[] filter = argsName.get("filter").split(",");
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        int indexName = 0;
        int indexAge = 0;
        try (Scanner scanner = new Scanner(new File(path))) {
            String firstRow = scanner.nextLine();
            List<String> list = List.of(firstRow.split(";"));
            if (list.contains(filter[0]) && list.contains(filter[1])) {
                indexName = list.indexOf(filter[0]);
                indexAge = list.indexOf(filter[1]);
                result.add(filter[0] + ";" + filter[1]);
            }
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                List<String> list1 = List.of(nextLine.split(delimiter));
                String name = list1.get(indexName);
                var age = list1.get(indexAge);
                result.add(name + ";" + age);
            }
            if (out.equals("stdout")) {
                for (String array : result) {
                    System.out.println(array);
                }
            } else {
                File file = new File(out);
                try (FileWriter fileWriter = new FileWriter(file)) {
                    for (String array : result) {
                        fileWriter.write(array);
                        fileWriter.write(System.lineSeparator());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 4) {
            throw new IllegalArgumentException("number of arguments isn`t correct");
        }
        File file = new File(args[0]);
        if (file.isFile()) {
            throw new IllegalArgumentException("path of source isn`t correct");
        }
        String[] filter = args[3].split(",");
        if (filter.length != 2) {
            throw new IllegalArgumentException("filter isn`t correct");
        }
        String delimiter = args[1];
        if (!delimiter.endsWith(";")) {
            throw new IllegalArgumentException("delimiter isn`t correct");
        }
        handle(ArgsName.of(new String[] {
                args[0], args[1], args[2], args[3]
        }));

    }
}
