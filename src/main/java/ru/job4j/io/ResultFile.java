package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static String[][] multiple(int size) {
        String[][] array = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i + 1) * (j + 1) < 10) {
                    array[i][j] = "" + (j + 1) + "x" + (i + 1) + "=" + (i + 1) * (j + 1) + " ";
                } else {
                    array[i][j] = "" + (j + 1) + "x" + (i + 1) + "=" + (i + 1) * (j + 1);
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            out.write("................................................................................".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("Multiplication table!".getBytes());

            out.write(System.lineSeparator().getBytes());
            out.write("................................................................................".getBytes());
            String[][] array = ResultFile.multiple(9);
            out.write(System.lineSeparator().getBytes());
            for (String[] r : array) {
                for (String c : r) {
                    out.write((c + " | ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
                out.write(".......|........|........|........|........|........|........|........|.........".getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}