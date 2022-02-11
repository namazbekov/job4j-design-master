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

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(Files.size(file), file.getFileName().toString());
        if (map.containsKey(fileProperty)) {
            map.get(fileProperty).add(file);
        } else {
            List<Path> list = new ArrayList<>();
            list.add(file);
            map.put(fileProperty, list);
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getDuplicateFiles() {
        List<Path> list = new ArrayList<>();
        for (Map.Entry<FileProperty, List<Path>> collect : map.entrySet()) {
            if (collect.getValue().size() > 1) {
                list.addAll(collect.getValue());
            }
        }
        return list;
    }
}
