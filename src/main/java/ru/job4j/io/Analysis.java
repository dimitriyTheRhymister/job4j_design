package ru.job4j.io;

import java.io.*;

public class Analysis {
    private boolean range = true;

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {
            reader.lines().forEach(line -> {
                boolean work = Integer.parseInt(line.substring(0, 1)) > 3;
                if (work && range) {
                    writer.print(line.substring(4) + ";");
                    range = false;
                }
                if (!work && !range) {
                    writer.println(line.substring(4) + ";");
                    range = true;
                }
            });
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