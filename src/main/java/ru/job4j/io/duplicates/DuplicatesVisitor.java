package ru.job4j.io.duplicates;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> map = new HashMap<>();
    private int c = 0;
    private final int count;

    public DuplicatesVisitor(int count) {
        this.count = count;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(
                Files.size(file),
                file.getFileName().toString());
        c++;
        List<Path> pathList = map.computeIfAbsent(fileProperty, k -> new ArrayList<>());
        pathList.add(file.toAbsolutePath());
        if (c == count) {
            for (FileProperty f : map.keySet()) {
                if (map.get(f).size() > 1) {
                    System.out.println(f.getName()
                            + " - "
                            + f.getSize());
                    map.get(f).forEach(System.out::println);
                }
            }
        }
        return super.visitFile(file, attrs);
    }
}