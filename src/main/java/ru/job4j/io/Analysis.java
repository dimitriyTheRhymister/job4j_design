package ru.job4j.io;

import java.io.*;

public class Analysis {
    private final StringBuilder out = new StringBuilder();
    private int range = 0;

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {
            reader.lines().forEach(line -> {
                if ((line.startsWith("400")
                        || line.startsWith("500"))
                && range == 0) {
                    out.append(line.substring(4));
                    out.append(";");
                    range = 1;
                }
                if ((line.startsWith("200")
                        || line.startsWith("300"))
                        && range == 1) {
                    out.append(line.substring(4));
                    out.append(";");
                    range = 0;
                    out.append("\n");
                }
            });
            writer.println(out);
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}