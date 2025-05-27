package ru.job4j.algo.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeTest {

    @Test
    void whenSortedThenOk(){
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    public void testNormalArray() {
        int[] unsorted = {38, 27, 43, 3, 9, 82, 10};
        int[] sorted = Merge.mergesort(unsorted);
        assertArrayEquals(new int[]{3, 9, 10, 27, 38, 43, 82}, sorted);
    }

    @Test
    public void testEmptyArray() {
        int[] unsorted = {};
        int[] sorted = Merge.mergesort(unsorted);
        assertArrayEquals(new int[]{}, sorted);
    }

    @Test
    public void testArrayWithDuplicates() {
        int[] unsorted = {5, 3, 5, 2, 5, 1};
        int[] sorted = Merge.mergesort(unsorted);
        assertArrayEquals(new int[]{1, 2, 3, 5, 5, 5}, sorted);
    }

    @Test
    public void testAlreadySortedArray() {
        int[] unsorted = {1, 2, 3, 4, 5, 6};
        int[] sorted = Merge.mergesort(unsorted);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, sorted);
    }

    @Test
    public void testReverseSortedArray() {
        int[] unsorted = {6, 5, 4, 3, 2, 1};
        int[] sorted = Merge.mergesort(unsorted);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, sorted);
    }
}