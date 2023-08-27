package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    private void validateArgs(ArgsName args) {
        String source = args.get("path");
        File sourceFile = new File(source);
        if (!sourceFile.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", sourceFile.getAbsoluteFile()));
        }
        boolean sourceExt = source.endsWith(".csv");
        if (!sourceExt) {
            throw new IllegalArgumentException("Wrong extension format for source argument");
        }
    }

    public static void handle(ArgsName argsName) throws Exception {
        var path = argsName.get("path");
        var delimiter = argsName.get("delimiter");
        var out = argsName.get("out");
        var filter = argsName.get("filter");

        Path sourceFilePath = Path.of(path);
        File sourceFile = sourceFilePath.toFile();
        var columnNumbers = new StringJoiner(" ");
        var resultColumns = new StringBuilder();

        var headerScanner = new Scanner(sourceFile);
        String[] headers = headerScanner.next().split(delimiter);
        for (int i = 0; i < headers.length; i++) {
            if (filter.contains(headers[i])) {
                columnNumbers.add(String.valueOf(i));
            }
        }
        headerScanner.close();

        var fileScanner = new Scanner(sourceFile);
        while (fileScanner.hasNextLine()) {
            String[] cells = fileScanner.nextLine().split(delimiter);
            for (int i = 0; i < cells.length; i++) {
                if (columnNumbers.toString().contains(String.valueOf(i))) {
                    resultColumns.append(cells[i]).append(delimiter);
                }
            }
            resultColumns = new StringBuilder(resultColumns.substring(0, resultColumns.length() - 1) + System.lineSeparator());
        }
        fileScanner.close();

        if ("stdout".equals(out)) {
            System.out.println(resultColumns);
        } else {
            Path targetFilePath = Path.of(out);
            File targetFile = targetFilePath.toFile();
            try (FileOutputStream fileOutputStream = new FileOutputStream(targetFile)) {
                fileOutputStream.write(resultColumns.toString().getBytes());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("The required number of Arguments was not passed to the program");
        }
        ArgsName argsName = ArgsName.of(args);
        CSVReader csvReader = new CSVReader();
        csvReader.validateArgs(argsName);
        handle(argsName);
    }
}