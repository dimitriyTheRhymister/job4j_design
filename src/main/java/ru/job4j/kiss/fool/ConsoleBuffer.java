package ru.job4j.kiss.fool;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleBuffer {

    private final ByteArrayOutputStream outputStream;
    private final PrintStream printStream;
    private final PrintStream originalOut;

    public ConsoleBuffer() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        originalOut = System.out;
    }

    public void startCapture() {
        System.setOut(printStream);
    }

    public String stopCapture() {
        System.setOut(originalOut);
        return outputStream.toString();
    }

    public void clear() {
        outputStream.reset();
    }
}