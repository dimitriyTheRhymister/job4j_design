package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        String value = null;
        for (String k : values.keySet()) {
            if (key.equals(k)) {
                value = values.get(k);
            }
        }
        return value;
    }

    private void parse(String[] args) {
        for (String s : args) {
            if (!s.startsWith("-")) {
                throw new IllegalArgumentException("Error: This argument '" + s + "' does not start with a '-' character");
            }
            if (!s.contains("=")) {
                throw new IllegalArgumentException("Error: This argument '" + s + "' does not contain an equal sign");
            }
            if (s.endsWith("=")
                    && !s.substring(0, s.length() - 1).contains("=")) {
                throw new IllegalArgumentException("Error: This argument '" + s + "' does not contain a value");
            }
            if (s.startsWith("-=")) {
                throw new IllegalArgumentException("Error: This argument '" + s + "' does not contain a key");
            }
            values.put(s.substring(1, s.indexOf("=")),
                    s.substring(s.indexOf("=") + 1));
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}