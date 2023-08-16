package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    private static void validation(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments!");
        }
        if (args[0].length() == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("It's not a file extension!");
        }
    }

    public static void main(String[] args) throws IOException {
        validation(args);
        Path start = Paths.get(args[0]);
        String extension = args[1];
        search(start, p -> p.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }

    public static List<Path> search(Path start, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(start, searcher);
        return searcher.getPaths();
    }
}