package ru.job4j.algo.tree.binarytree;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class AvlTreeTest {

    @Test
    void testSymmetricalTraversalReturnsSortedElements() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void testContains() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(5);
        assertTrue(tree.contains(5));
        assertFalse(tree.contains(10));
    }

    @Test
    void testContainsAfterInsertingDuplicate() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(5);
        assertTrue(tree.contains(5));
        tree.insert(5);
        assertTrue(tree.contains(5));
        assertEquals(1, tree.inPreOrder().size());
    }

    @Test
    void testInsert() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(5);
        tree.insert(10);
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(10));
    }

    @Test
    void testRemove() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(5);
        tree.insert(10);
        tree.remove(5);
        assertFalse(tree.contains(5));
        assertTrue(tree.contains(10));
    }

    @Test
    void testMinimum() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        assertEquals(3, tree.minimum());
    }

    @Test
    void testMaximum() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        assertEquals(7, tree.maximum());
    }

    @Test
    void testInSymmetricalOrder() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        assertEquals(List.of(3, 5, 7), tree.inSymmetricalOrder());
    }

    @Test
    void testInPreOrder() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        assertEquals(List.of(5, 3, 7), tree.inPreOrder());
    }

    @Test
    void testInPostOrder() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        assertEquals(List.of(3, 7, 5), tree.inPostOrder());
    }

    @Test
    void testBalanceAfterInsert() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 0; i < 10; i++) {
            tree.insert(i);
        }
        assertTrue(tree.isBalanced());
    }

    @Test
    void testBalanceAfterRemove() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 0; i < 10; i++) {
            tree.insert(i);
        }
        tree.remove(5);
        assertTrue(tree.isBalanced());
    }
}