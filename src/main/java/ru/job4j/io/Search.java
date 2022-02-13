package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root is null");
        }
        if (args[args.length - 1] == null) {
            throw new NoSuchObjectException("Please, enter extension");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException("Directory is empty");
        }
        if (!args[args.length - 1].startsWith(".txt")) {
            throw new NoSuchObjectException("no such extension");
        }
            Path start = Paths.get(args[0]);
            search(start, p -> p.toFile().getName().endsWith(args[args.length - 1])).forEach(System.out::println);

    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}