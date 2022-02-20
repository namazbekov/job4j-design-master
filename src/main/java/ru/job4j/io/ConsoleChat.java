package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
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
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String yourString = null;
            boolean status = true;
            System.out.println("Enter someThing :");
            while (!(OUT).equals(yourString)) {
                yourString = br.readLine();
                log.add(yourString);
                if ((STOP).equals(yourString)) {
                    status = false;
                }
                if ((CONTINUE).equals(yourString)) {
                    status = true;
                }
                if (status) {
                    List<String> answer = readPhrases();
                    Random random = new Random();
                    String randomElement = answer.get(random.nextInt(answer.size()));
                    log.add(randomElement);
                    System.out.println("ответ на запрос :" + randomElement);
                }
            }
            saveLog(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        String phrases;
        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
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
        ConsoleChat cc = new ConsoleChat("./data/text1.txt", "");
        cc.run();
    }
}
