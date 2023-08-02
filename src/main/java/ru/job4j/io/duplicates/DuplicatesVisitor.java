package ru.job4j.io.duplicates;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    public static Map<FileProperty, List<Path>> map = new HashMap<>();

    public static void printResult() {
        for (FileProperty f : map.keySet()) {
            if (map.get(f).size() > 1) {
                System.out.println(f.getName()
                        + " - "
                        + f.getSize());
                map.get(f).forEach(System.out::println);
            }
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(
                Files.size(file),
                file.getFileName().toString());
        List<Path> pathList = map.computeIfAbsent(fileProperty, k -> new ArrayList<>());
        pathList.add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }
}