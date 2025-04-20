package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoolTest {

    @Test
    void testGetExpectedAnswer() {
        Fool fool = new Fool();
        assertEquals("1", fool.getExpectedAnswer(1));
        assertEquals("Fizz", fool.getExpectedAnswer(3));
        assertEquals("Buzz", fool.getExpectedAnswer(5));
        assertEquals("FizzBuzz", fool.getExpectedAnswer(15));
    }

    @Test
    void testPlayGameFirstStepsThanMistake() {
        ConsoleBuffer outBuffer = new ConsoleBuffer();
        outBuffer.startCapture();

        try {
            String inputToSimulate = "2\n4\n9\n";
            System.setIn(new java.io.ByteArrayInputStream(inputToSimulate.getBytes()));

            new Fool().playGame();

            String actualOutput = outBuffer.stopCapture();
            System.out.println(actualOutput);
            outBuffer.clear();

            String expected = "Game FizzBuzz.\n1\nFizz\nBuzz\nMistake. Start again.\n1\n";

            assertEquals(expected, actualOutput.replaceAll("\\r", ""));

        } finally {
            outBuffer.clear();
            System.setIn(System.in);
        }
    }
}