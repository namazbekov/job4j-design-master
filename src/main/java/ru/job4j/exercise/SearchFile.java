package ru.job4j.exercise;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFile {
    private static List<Path> list = null;
    public static void main(String[] args) throws IOException {
       ArgsName argsName = ArgsName.of(args);
       Path source = Path.of(argsName.get("d"));
       String character = argsName.get("n");
       String mask = argsName.get("t");
       Path target = Path.of(argsName.get("o"));
       if (args.length != 4) {
           throw new IllegalArgumentException("you provided few arguments");
       }
       File file = source.toFile();
       if (!file.isDirectory()) {
           throw new IllegalArgumentException("source isn`t directory");
       }
       File file2 = target.toFile();
       if (!file2.isFile()) {
           throw new IllegalArgumentException("your target isn`t file");
       }
       if (character.isEmpty() || mask.isEmpty() || target.toString().isEmpty() || source.toString().isEmpty()) {
           throw new IllegalArgumentException("we have an empty argument");
       }
       if (mask.equals("name")) {
           list = Search.search(source, s -> s.toFile().getName().equals(character));
           System.out.println(list);
       }
       if (mask.equals("mask")) {
           Pattern ask = Pattern.compile("\\.");
           Matcher matcher = ask.matcher(character);
           String newString = matcher.replaceAll("\\\\.");
           Pattern star = Pattern.compile("\\*");
           Matcher matcher1 = star.matcher(newString);
           String newString1 = matcher1.replaceAll("\\.*");
           Pattern questionMark = Pattern.compile("\\?");
           Matcher matcher2 = questionMark.matcher(newString1);
           String newString3 = matcher2.replaceAll("\\.");
           String result = "^" + newString3 + "$";
           list = Search.search(source, s -> s.toFile().getName().matches(result));
           System.out.println(list);
       }
       if (mask.equals("regex")) {
           Pattern pattern = Pattern.compile((argsName.get("n")));
           list = Search.search(Paths.get(argsName.get("d")), p -> pattern.matcher(p.toFile().getName()).find());
           System.out.println(list);
       }
       try (FileWriter write = new FileWriter(String.valueOf(target))) {
           for (Path p : list) {
               write.write(String.valueOf(p));
               write.write(System.lineSeparator());
           }
       }
    }
}
