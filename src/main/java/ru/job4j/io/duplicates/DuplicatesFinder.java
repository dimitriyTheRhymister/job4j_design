package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path root = Paths.get("./");
        NumberOfFiles testClass = new NumberOfFiles();
        int count = testClass.count(root.toFile());
        Files.walkFileTree(root, new DuplicatesVisitor(count));
    }
}