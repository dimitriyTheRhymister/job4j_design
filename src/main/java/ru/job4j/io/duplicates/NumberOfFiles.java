package ru.job4j.io.duplicates;

import java.util.*;
import java.io.*;

public class NumberOfFiles {
    private int count = 0;

    public int count(File dir) {
        for (File fileEntry : Objects.requireNonNull(dir.listFiles())) {
            if (fileEntry.isDirectory()) {
                count(fileEntry);
            } else {
                count++;
            }
        }
        return count;
    }
}