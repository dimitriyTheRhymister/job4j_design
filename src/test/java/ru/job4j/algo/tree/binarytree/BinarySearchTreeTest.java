package ru.job4j.algo.tree.binarytree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTreeTest {
    @Test
    void whenAddToEmptyTreeThenListContainsOneElement() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThat(tree.put("first")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(1)
                .containsExactly("first");
    }

    @Test
    void whenAddTwoToEmptyTreeThenListContainsTwoElement() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThat(tree.put("first")).isTrue();
        assertThat(tree.put("second")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(2)
                .containsExactly("first", "second");
    }

    @Test
    void whenAddElementThenContainElementOk() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.put("first");
        tree.put("second");
        tree.put("three");
        assertThat(tree.contains("second")).isTrue();
        assertThat(tree.contains("four")).isFalse();
    }

    @Test
    void whenAddMaximumNotEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 8, 7}) {
            tree.put(element);
        }
        assertThat(tree.maximum()).isEqualTo(8);
    }

    @Test
    void whenAddMaximumIsEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(element);
        }
        assertThat(tree.maximum()).isEqualTo(7);
    }

    @Test
    void whenAddMinimumIsEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.minimum()).isEqualTo(1);
    }

    @Test
    void whenAddMinimumIsNotEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7}) {
            tree.put(element);
        }
        assertThat(tree.minimum()).isEqualTo(2);
    }

    @Test
    void whenSymmetricalOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inSymmetricalOrder()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenPreOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inPreOrder()).hasSize(7)
                .containsExactly(4, 2, 1, 3, 6, 5, 7);
    }

    @Test
    void whenPostOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inPostOrder()).hasSize(7)
                .containsExactly(1, 3, 2, 5, 7, 6, 4);
    }

    @Test
    public void testRemoveLeafNode() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.put(5);
        bst.put(3);
        bst.put(7);
        bst.put(2);
        bst.put(4);
        bst.put(8);

        assertTrue(bst.remove(2));
        assertFalse(bst.contains(2));
    }

    @Test
    public void testRemoveNodeWithOneChild() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.put(5);
        bst.put(3);
        bst.put(7);
        bst.put(2);
        bst.put(4);
        bst.put(8);

        assertTrue(bst.remove(7));
        assertFalse(bst.contains(7));
        assertTrue(bst.contains(8));
    }

    @Test
    public void testRemoveNodeWithTwoChildren() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.put(5);
        bst.put(3);
        bst.put(7);
        bst.put(2);
        bst.put(4);
        bst.put(6);
        bst.put(8);

        assertTrue(bst.remove(7));
        assertFalse(bst.contains(7));
        assertTrue(bst.contains(6));
        assertTrue(bst.contains(8));
    }

    @Test
    public void testRemoveRootNode() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.put(5);
        bst.put(3);
        bst.put(7);
        bst.put(2);
        bst.put(4);
        bst.put(6);
        bst.put(8);

        assertTrue(bst.remove(5));
        assertFalse(bst.contains(5));
    }

    @Test
    public void testRemoveNonExistingNode() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.put(5);
        bst.put(3);
        bst.put(7);
        bst.put(2);
        bst.put(4);
        bst.put(6);
        bst.put(8);

        assertFalse(bst.remove(9));
    }

    @Test
    public void testRemoveFromEmptyTree() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        assertFalse(bst.remove(5));
    }

    @Test
    public void testClear() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.put(5);
        bst.put(3);
        bst.put(7);
        bst.put(2);
        bst.put(4);
        bst.put(6);
        bst.put(8);

        assertTrue(bst.contains(5));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(7));
        assertTrue(bst.contains(2));
        assertTrue(bst.contains(4));
        assertTrue(bst.contains(6));
        assertTrue(bst.contains(8));

        bst.clear();

        assertFalse(bst.contains(5));
        assertFalse(bst.contains(3));
        assertFalse(bst.contains(7));
        assertFalse(bst.contains(2));
        assertFalse(bst.contains(4));
        assertFalse(bst.contains(6));
        assertFalse(bst.contains(8));
    }

    @Test
    public void testClearEmptyTree() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.clear();

        assertTrue(bst.isEmpty());
    }

    @Test
    public void testClearTreeWithOneElement() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.put(5);

        assertFalse(bst.isEmpty());
        assertTrue(bst.contains(5));

        bst.clear();

        assertTrue(bst.isEmpty());
        assertFalse(bst.contains(5));
    }
}