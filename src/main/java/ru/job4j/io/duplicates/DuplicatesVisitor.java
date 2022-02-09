package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    HashMap<FileProperty, List<Path>> map = new HashMap<>();
    List<Path> list = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        collectDuplicateFiles(file);
        return super.visitFile(file, attrs);
    }

    private void collectDuplicateFiles(Path file) throws IOException {
        FileProperty duplicate = new FileProperty(Files.size(file), file.getFileName().toString());
        list.add(file.toAbsolutePath());
        map.put(duplicate, list);
        if (!map.containsKey(duplicate)) {
            List<Path> originalFile = new ArrayList<>();
            originalFile.add(file.toAbsolutePath());
            map.put(duplicate, originalFile);
        } else {
            list.add(file.toAbsolutePath());
        }
    }

    public void printDuplicateFiles() {
        Set<Map.Entry<FileProperty, List<Path>>> set = map.entrySet();
        for (Map.Entry<FileProperty, List<Path>> collect : set) {
            if (list.size() > 1)
            System.out.println(collect.getKey().getName() + " - " + collect.getValue());
        }
    }
}
