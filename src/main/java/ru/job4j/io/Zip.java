package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path file : sources) {
                zip.putNextEntry(new ZipEntry(String.valueOf(file)));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(String.valueOf(file)))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        ArgsName name = ArgsName.of(args);
        Path source = Path.of(name.get("d"));
        String targetName = name.get("o");
        String character = name.get("e");
        File directory = source.toFile();
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("no such that directory");
        }
        if (!character.startsWith(".")) {
            throw new IllegalArgumentException("your extension have to start with point");
        }
        List<Path> sources = Search.search(source, s -> !s.toFile().getName().endsWith(character));
        File target = new File(targetName);
        packFiles(sources, target);
    }
}