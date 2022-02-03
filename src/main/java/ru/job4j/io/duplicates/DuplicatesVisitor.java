package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Objects;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    HashMap<Path, Path> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty value = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (Objects.equals(value.getName(), file.getFileName().toString())
                && value.getSize() == file.toString().length()) {
            map.put(file.getFileName(), file.toAbsolutePath());
            System.out.println(map);
        }
        return super.visitFile(file, attrs);
    }
}
