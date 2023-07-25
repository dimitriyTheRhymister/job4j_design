package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(line -> !line.startsWith("#"))
                    .filter(line -> line.length() != 0)
                    .forEach(line -> {
                        if (!line.contains("=")
                                || line.startsWith("=")
                                || (line.endsWith("=")
                                && line.indexOf("=") == line.length() - 1)) {
                            throw new IllegalArgumentException("IllegalArgument!");
                        }
                        String[] strings = line.split("=");
                        values.put(strings[0], strings[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        String value = null;
        for (String k : values.keySet()) {
            if (k.equals(key)) {
                value = values.get(k);
            }
        }
        return value;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

}