package ru.job4j.algo.tree;

import java.util.*;
import java.util.function.Consumer;

public class TreeUtils<T> {

    private void traverse(Node<T> root, Consumer<T> consumer) {
        if (root == null) {
            throw new IllegalArgumentException("Корневой узел не может быть null");
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            consumer.accept(node.getValue());

            queue.addAll(node.getChildren());
        }
    }

    /**
     * Метод выполняет обход дерева и считает количество узлов
     *
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        int[] count = new int[1];
        traverse(root, value -> count[0]++);
        return count[0];
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     *
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        List<T> result = new ArrayList<>();
        traverse(root, result::add);
        return result;
    }

    public static void main(String[] args) {
        Node<String> root = new Node<>("A",
                new Node<>("B",
                        new Node<>("D"),
                        new Node<>("E")
                ),
                new Node<>("C",
                        new Node<>("F")
                )
        );

        TreeUtils<String> treeUtils = new TreeUtils<>();
        System.out.println(treeUtils.countNode(root));
        System.out.println(treeUtils.findAll(root));
    }
}