package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final Map<Integer, String> botAnswersMap = new HashMap<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private void readFromAnswersFile() {
        try (BufferedReader readerFromFile = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8))) {
            String line = readerFromFile.readLine();
            while (line != null) {
                botAnswersMap.put(Integer.valueOf(line.split("\\.\\s")[0]), line.split("\\.\\s")[1]);
                line = readerFromFile.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getRandomPhrase() {
        Random random = new Random();
        int randomInt = random.nextInt(botAnswersMap.size());
        String botAnswer = "";
        for (Integer key : botAnswersMap.keySet()) {
            if (key == randomInt) {
                botAnswer = botAnswersMap.get(key);
            }
        }
        return botAnswer;
    }

    private void writeToLogFile(List<String> phrasesToLog) {
        try (BufferedWriter writerToFile = new BufferedWriter(new FileWriter(botAnswers, StandardCharsets.UTF_8))) {
            phrasesToLog.forEach(s -> {
                try {
                    writerToFile.write(s);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void runConsoleChat() {
        List<String> phrasesToLog = new ArrayList<>();
        System.out.println("О чём желаете говорить?");
        phrasesToLog.add("Bot: " + "О чём желаете говорить?" + "\n");
        boolean flag = true;
        readFromAnswersFile();
        try (BufferedReader readerFromConsol = new BufferedReader(new InputStreamReader(System.in))) {
            String userWord = readerFromConsol.readLine();
            while (!userWord.equals(OUT)) {
                if (userWord.equals(STOP)
                        || userWord.equals(CONTINUE)) {
                    flag = !flag;
                }
                phrasesToLog.add("User: " + userWord + "\n");
                if (flag) {
                    String randomString = getRandomPhrase();
                    phrasesToLog.add("Bot: " + randomString + "\n");
                    System.out.println(randomString);
                }
                userWord = readerFromConsol.readLine();
            }
            phrasesToLog.add("User: " + userWord);
            writeToLogFile(phrasesToLog);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/botAnswers.txt", "data/consoleChatLog.txt");
        consoleChat.runConsoleChat();
    }
}
