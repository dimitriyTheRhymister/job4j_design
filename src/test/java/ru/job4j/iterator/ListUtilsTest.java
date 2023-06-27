package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddBeforeThenAddAfter() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(4).containsSequence(1, 2, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addBefore(input, 1, 22);
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 0, 2);
        ListUtils.removeIf(input, i -> i == 2);
        assertThat(input).hasSize(3).containsSequence(1, 22, 3);
    }

    @Test
    void whenReplaceIf() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addBefore(input, 1, 2);
        ListUtils.replaceIf(input, i -> i == 2, 22);
        assertThat(input).hasSize(4).containsSequence(1, 22, 22, 3);
    }

    @Test
    void whenRemoveAll() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addAfter(input, 0, 22);
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(elements, 1, 2);
        ListUtils.addBefore(elements, 1, 2);
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(1).containsSequence(22);
    }
}