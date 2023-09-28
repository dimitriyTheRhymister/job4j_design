package ru.job4j.searcher;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class CriterionSearch {
    private void validateArgs(ArgsName argsName) {
        File directory = new File(argsName.get("d"));
        if (!directory.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", directory.getAbsoluteFile()));
        }
        String searchType = argsName.get("t");
        if (!("mask".equals(searchType)
                || "name".equals(searchType)
                || "regex".equals(searchType))) {
            throw new IllegalArgumentException("Wrong type name");
        }
        String targetFileExt = argsName.get("o").split("\\.")[1];
        if (!"txt".equals(targetFileExt)) {
            throw new IllegalArgumentException("Wrong target file extension format");
        }
    }

    private void writeSearchResultsToFile(Path path,  List<Path> pathList) {
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(path.toString(), true)));
            for (Path p : pathList) {
                writer.println(p);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("The required number of Arguments was not passed to the program");
        }
        CriterionSearch searcher = new CriterionSearch();
        ArgsName argsName = ArgsName.of(args);
        searcher.validateArgs(argsName);
        File directory = new File(argsName.get("d"));
        String searchPattern = argsName.get("n");
        String searchType = argsName.get("t");
        Path path = Path.of(argsName.get("o"));
        if ("mask".equals(searchType)) {
            searchPattern = searchPattern.replace("?", "\\S");
            searchPattern = searchPattern.replace("*", "\\S*");
        }
        String finalSearchPattern = searchPattern;
        List<Path> pathList = Search.search(directory.toPath(), p -> p.toFile().getName().matches(finalSearchPattern));
        searcher.writeSearchResultsToFile(path, pathList);
    }
}
/*
parameters for tests:
-d=C:/projects/job4j_design/data/a -n=404.txt -t=name -o=src/main/java/ru/job4j/searcher/log.txt
-d=C:/projects/job4j_design/data/a "-n=\S*\.\S{2,3}" -t=regex -o=src/main/java/ru/job4j/searcher/log.txt
--d=C:/projects/job4j_design/data/a -n=*.?s -t=mask -o=src/main/java/ru/job4j/searcher/log.txt
*/