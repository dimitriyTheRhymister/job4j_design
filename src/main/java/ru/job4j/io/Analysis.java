package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Analysis {
    private final StringBuilder out = new StringBuilder();
    private boolean range = false;
    private int index = 0;
    private String[] strings;

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            Stream<String> stringsStream = read.lines();
            List<String> stringList = stringsStream.toList();
            strings = stringList.toArray(new String[0]);
            while (index < strings.length - 1) {
                addRangeBorder("400", "500");
                addRangeBorder("200", "300");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {
                writer.println(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addRangeBorder(String status1, String status2) {
        for (int i = index; i < strings.length; i++) {
            if (strings[i].startsWith(status1)
                    || strings[i].startsWith(status2)) {
                out.append(strings[i].substring(4));
                out.append(";");
                range = !range;
                index = i;
                break;
            }
        }
        if (Objects.equals(status1, "200") || Objects.equals(status2, "300")) {
            out.append("\n");
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}