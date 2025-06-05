package ru.job4j.algo.tree.binarytree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class TreeAVLMapTest {

    @Test
    void whenEmptyTree() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.values()).isEmpty();
        assertThat(tree.keySet()).isEmpty();
    }

    @Test
    void whenAddOneElemThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.put(1, "11")).isTrue();
        assertThat(tree.values()).hasSize(1)
                .containsExactly("11");
        assertThat(tree.keySet()).hasSize(1)
                .containsExactly(1);
    }

    @Test
    void whenAddSevenElemThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.put(1, "11")).isTrue();
        assertThat(tree.put(2, "22")).isTrue();
        assertThat(tree.put(3, "33")).isTrue();
        assertThat(tree.put(4, "44")).isTrue();
        assertThat(tree.put(5, "55")).isTrue();
        assertThat(tree.put(6, "66")).isTrue();
        assertThat(tree.put(7, "77")).isTrue();
        assertThat(tree.values()).hasSize(7)
                .containsExactly("11", "22", "33", "44", "55", "66", "77");
        assertThat(tree.keySet()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenUpdateValueThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(1, "11");
        tree.put(2, "22");
        tree.put(3, "33");
        tree.put(4, "44");
        tree.put(5, "55");
        tree.put(6, "66");
        tree.put(7, "77");
        assertThat(tree.put(5, "555")).isTrue();
        assertThat(tree.values()).hasSize(7)
                .containsExactly("11", "22", "33", "44", "555", "66", "77");
        assertThat(tree.keySet()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenDeleteKeyThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(1, "11");
        tree.put(2, "22");
        tree.put(3, "33");
        tree.put(4, "44");
        tree.put(5, "55");
        tree.put(6, "66");
        tree.put(7, "77");
        assertThat(tree.remove(5)).isTrue();
        assertThat(tree.remove(0)).isFalse();
        assertThat(tree.values()).hasSize(6)
                .containsExactly("11", "22", "33", "44", "66", "77");
        assertThat(tree.keySet()).hasSize(6)
                .containsExactly(1, 2, 3, 4, 6, 7);
    }

    @Test
    void whenGetKeyThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(1, "11");
        tree.put(2, "22");
        tree.put(3, "33");
        tree.put(4, "44");
        tree.put(5, "55");
        tree.put(6, "66");
        tree.put(7, "77");
        assertThat(tree.get(5)).isEqualTo("55");
        assertThat(tree.get(0)).isNull();
    }

    @Test
    void testPutNewKey() {
        TreeAVLMap<Integer, String> map = new TreeAVLMap<>();
        boolean result = map.put(1, "one");
        assertTrue(result);
        assertEquals("one", map.get(1));
    }

    @Test
    void testPutExistingKey() {
        TreeAVLMap<Integer, String> map = new TreeAVLMap<>();
        map.put(1, "one");
        boolean result = map.put(1, "new one");
        assertTrue(result);
        assertEquals("new one", map.get(1));
    }

    @Test
    void testPutNullKey() {
        TreeAVLMap<Integer, String> map = new TreeAVLMap<>();
        boolean result = map.put(null, "null");
        assertFalse(result);
    }

    @Test
    void testPutNullValue() {
        TreeAVLMap<Integer, String> map = new TreeAVLMap<>();
        boolean result = map.put(1, null);
        assertFalse(result);
    }

    @Test
    void testRemoveExistingKey() {
        TreeAVLMap<Integer, String> map = new TreeAVLMap<>();
        map.put(1, "one");
        boolean result = map.remove(1);
        assertTrue(result);
        assertNull(map.get(1));
    }

    @Test
    void testRemoveNonExistingKey() {
        TreeAVLMap<Integer, String> map = new TreeAVLMap<>();
        boolean result = map.remove(1);
        assertFalse(result);
    }

    @Test
    void testGetExistingKey() {
        TreeAVLMap<Integer, String> map = new TreeAVLMap<>();
        map.put(1, "one");
        String result = map.get(1);
        assertEquals("one", result);
    }

    @Test
    void testGetNonExistingKey() {
        TreeAVLMap<Integer, String> map = new TreeAVLMap<>();
        String result = map.get(1);
        assertNull(result);
    }

    @Test
    void testKeySet() {
        TreeAVLMap<Integer, String> map = new TreeAVLMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        var keys = map.keySet();
        assertEquals(3, keys.size());
        assertTrue(keys.contains(1));
        assertTrue(keys.contains(2));
        assertTrue(keys.contains(3));
    }

    @Test
    void testValues() {
        TreeAVLMap<Integer, String> map = new TreeAVLMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        var values = map.values();
        assertEquals(3, values.size());
        assertTrue(values.contains("one"));
        assertTrue(values.contains("two"));
        assertTrue(values.contains("three"));
    }

    @Test
    void testMultiplePutAndRemove() {
        TreeAVLMap<Integer, String> map = new TreeAVLMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(i, String.valueOf(i));
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(String.valueOf(i), map.get(i));
        }
        for (int i = 0; i < 100; i++) {
            map.remove(i);
            assertNull(map.get(i));
        }
    }
}