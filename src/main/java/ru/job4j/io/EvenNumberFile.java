package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            StringBuilder numText = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                    numText.append((char) read);
            }
            String[] numLines = numText.toString().split(System.lineSeparator());
            for (String numLine : numLines) {
                if (Integer.parseInt(numLine) % 2 == 0) {
                    System.out.println(numLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}