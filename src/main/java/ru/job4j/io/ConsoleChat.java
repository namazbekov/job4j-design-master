package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String source;
    private final String botAnswers;

    public ConsoleChat(String source, String botAnswers) {
        this.source = source;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        boolean status = true;
        try (Scanner scanner = new Scanner(System.in)) {
            List<String> answer = readPhrases();
            Random random = new Random();
            String yourString = null;
            System.out.println("Enter someThing :");
            while (!(OUT).equals(yourString)) {
                yourString = scanner.next();
                if ((STOP).equals(yourString)) {
                    status = false;
                }
                if ((CONTINUE).equals(yourString)) {
                    status = true;
                }
                if (status && !OUT.equals(yourString)) {
                    log.add(yourString);
                    String randomElement = answer.get(random.nextInt(answer.size()));
                    log.add(randomElement);
                    System.out.println("ответ на запрос :" + randomElement);
                }
                log.add(yourString);
                saveLog(log);
            }
        }
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        String phrases;
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            while ((phrases = br.readLine()) != null) {
                list.add(phrases);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter print = new PrintWriter(new FileWriter(this.source, StandardCharsets.UTF_8), true)) {
            log.forEach(print::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("./data/text2.txt", "./data/text1.txt");
        cc.run();
    }
}
