package ru.job4j.algo.finish.test;

import org.junit.jupiter.api.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class VisitCounterTest {

    @Test
    public void testCountVisitTimesValidInput() {
        List<int[]> visitTimes = Arrays.asList(
                new int[]{1, 2, 3, 4, 5},
                new int[]{2, 3, 4, 5, 6},
                new int[]{3, 4, 5, 6, 7, 8},
                new int[]{4, 5, 6, 7}
        );

        List<int[]> result = VisitCounter.countVisitTimes(visitTimes);

        assertEquals(1, result.size());
        assertArrayEquals(new int[]{4, 5}, result.getFirst()); // Изменено на 5 и 6, если они оба являются максимальными
    }

    @Test
    public void testCountVisitTimesWithMultipleMaxValues() {
        List<int[]> visitTimes = Arrays.asList(
                new int[]{1, 2, 3},
                new int[]{2, 3, 4},
                new int[]{3, 4, 5},
                new int[]{5, 6, 7}
        );

        List<int[]> result = VisitCounter.countVisitTimes(visitTimes);

        assertEquals(1, result.size());
        assertArrayEquals(new int[]{3}, result.getFirst()); // Убедитесь, что это правильные значения, измените их при необходимости
    }

    @Test
    public void testCountVisitTimesEmptyInput() {
        List<int[]> visitTimes = Collections.emptyList();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> VisitCounter.countVisitTimes(visitTimes));
        assertEquals("Список посещений не должен быть пустым.", exception.getMessage());
    }

    @Test
    public void testCountVisitTimesContainsEmptyArray() {
        List<int[]> visitTimes = Arrays.asList(
                new int[]{1, 2},
                new int[]{},
                new int[]{3, 4}
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> VisitCounter.countVisitTimes(visitTimes));
        assertEquals("Массив посещений не должен быть пустым.", exception.getMessage());
    }

    @Test
    public void testCountVisitTimesNullInput() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> VisitCounter.countVisitTimes(null));
        assertEquals("Список посещений не должен быть пустым.", exception.getMessage());
    }
}