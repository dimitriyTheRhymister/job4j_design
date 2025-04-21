package ru.job4j.template;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleGeneratorTest {

    @Test
    public void whenProduceThenReturnCorrectString() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of("name", "Petr", "subject", "you");
        String expected = "I am a Petr, Who are you?";
        String result = generator.produce(template, args);
        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyNotFoundInArgsThenThrowException() {
        Generator generator = new SimpleGenerator();
        String template = "Hello, ${name}!";
        Map<String, String> args = Map.of("surname", "Petrov");
        generator.produce(template, args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenExtraKeyInArgsThenThrowException() {
        Generator generator = new SimpleGenerator();
        String template = "Hello, ${name}!";
        Map<String, String> args = Map.of("name", "Petr", "age", "30");
        generator.produce(template, args);
    }

    @Test
    public void whenTemplateIsEmptyThenReturnEmptyString() {
        Generator generator = new SimpleGenerator();
        String template = "";
        Map<String, String> args = Map.of();
        String result = generator.produce(template, args);
        assertEquals("", result);
    }

    @Test
    public void whenArgsIsEmptyAndTemplateHasNoKeysThenReturnTemplate() {
        Generator generator = new SimpleGenerator();
        String template = "Hello!";
        Map<String, String> args = Map.of();
        String result = generator.produce(template, args);
        assertEquals("Hello!", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArgsIsEmptyAndTemplateHasKeysThenThrowException() {
        Generator generator = new SimpleGenerator();
        String template = "Hello, ${name}!";
        Map<String, String> args = Map.of();
        generator.produce(template, args);
    }

    @Test
    public void whenExtraKeyInArgsThenThrowExceptionMessage() {
        Generator generator = new SimpleGenerator();
        String template = "Hello, ${name}!";
        Map<String, String> args = Map.of("name", "Petr", "age", "30");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> generator.produce(template, args));

        assertTrue(exception.getMessage().contains("Extra key 'age' found in args"));
    }
}
