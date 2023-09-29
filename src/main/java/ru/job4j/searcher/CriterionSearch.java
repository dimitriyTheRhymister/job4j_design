package ru.job4j.searcher;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

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

    private String makePredicate(String searchPattern, String searchType) {
        if ("mask".equals(searchType)) {
            searchPattern = searchPattern
                    .replace(".", "[.]")
                    .replace("?", "\\S")
                    .replace("*", "\\S*");
        }
        return searchPattern;
    }

    private List<Path> searchFiles(File directory, String predicate) throws IOException {
        System.out.println(predicate);
        return Search.search(directory.toPath(), p -> p.toFile().getName().matches(predicate));
    }

    private void writeSearchResultsToFile(Path path,  List<Path> pathList) {
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(path.toString(), true)));
            for (Path p : pathList) {
                writer.println(p);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
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
        String predicate = searcher.makePredicate(searchPattern, searchType);
        List<Path> pathList = searcher.searchFiles(directory, predicate);
        System.out.println(pathList);
        searcher.writeSearchResultsToFile(path, pathList);
    }
}