package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects/job4j_design");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println("The directory \"c:\\projects/job4j_design\" contains the following files:");
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            if (subfile.isFile()) {
                Long fileSize = subfile.length();
                String fileName = subfile.toString().substring(25);
                System.out.printf("- name: %s, size: %d Bytes;%n", fileName, fileSize);
            }
        }
    }
}