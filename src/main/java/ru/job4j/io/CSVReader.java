package ru.job4j.io;

import java.io.*;
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
        String[] filterArr = filter.split(",");
        int[] columnNumbersArr = new int[filterArr.length];
        File sourceFile = new File(path);
        var resultColumns = new StringBuilder();

        var headerScanner = new Scanner(sourceFile);
        String[] headers = headerScanner.next().split(delimiter);
        for (int i = 0; i < filterArr.length; i++) {
            for (int j = 0; j < headers.length; j++) {
                if (Objects.equals(filterArr[i], headers[j])) {
                    columnNumbersArr[i] = j;
                }
            }
        }
        headerScanner.close();

        var fileScanner = new Scanner(sourceFile);
        while (fileScanner.hasNextLine()) {
            String[] cells = fileScanner.nextLine().split(delimiter);
            for (int columnNumber : columnNumbersArr) {
                for (int j = 0; j < cells.length; j++) {
                    if (columnNumber == j) {
                        resultColumns.append(cells[j]).append(delimiter);
                    }
                }
            }
            resultColumns = new StringBuilder(resultColumns.substring(0, resultColumns.length() - 1) + System.lineSeparator());
        }
        fileScanner.close();

        if ("stdout".equals(out)) {
            System.out.println(resultColumns);
        } else {
            File targetFile = new File(out);
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