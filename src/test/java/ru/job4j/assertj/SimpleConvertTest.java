package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array)
                .hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .allMatch(e -> e.length() < 10)
                .anySatisfy(e -> assertThat(e).isEqualTo("five"))
                .filteredOn(e -> e.length() == 4)
                .startsWith("four");
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five", "second", "three", "four", "five");
        assertThat(list)
                .hasSize(9)
                .containsOnly("first", "second", "three", "four", "five")
                .doesNotContain("first", Index.atIndex(5))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first1")
                .anyMatch(e -> e.length() == 6)
                .anySatisfy(e -> assertThat(e.length()).isLessThan(5))
                .filteredOnAssertions(e -> assertThat(e.length())
                        .isLessThan(5))
                .hasSize(4)
                .last()
                .isNotNull()
                .isInstanceOf(String.class);
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five", "second", "three", "four", "five");
        assertThat(set)
                .hasSize(5)
                .containsExactlyInAnyOrder("first", "second", "three", "four", "five")
                .contains("first", "second")
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first1", "second2")
                .anyMatch(e -> e.length() == 6)
                .noneMatch(e -> e.length() == 10)
                .anySatisfy(e -> {
                    assertThat(e).isEqualTo("five");
                    assertThat(e.length()).isLessThan(7);
                })
                .filteredOn(e -> e.length() == 4)
                .filteredOnAssertions(e -> assertThat(e.length()).isLessThan(5))
                .hasSize(2)
                .contains("four", "five");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> list = simpleConvert.toMap("first", "second", "three", "four", "five", "second", "three", "four", "five");
        assertThat(list)
                .hasSize(5)
                .hasSizeBetween(3, 9)
                .containsKeys("second", "three", "four", "five")
                .doesNotContainKey("zero")
                .containsValues(1, 2, 3)
                .doesNotContainValue(222)
                .containsEntry("five", 4)
                .isNotEmpty()
                .isInstanceOfAny(HashMap.class, HashSet.class);
    }
}