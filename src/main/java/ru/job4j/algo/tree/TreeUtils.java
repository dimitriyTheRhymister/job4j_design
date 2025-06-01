package ru.job4j.algo.tree;

import ru.job4j.collection.SimpleStack;

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

    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        checkRoot(root);

        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);

        do {
            Node<T> node = stack.pop();
            if (node.getValue().equals(key)) {
                return Optional.of(node);
            }

            for (Node<T> child : node.getChildren()) {
                stack.push(child);
            }

        } while (!stack.isEmpty());

        return Optional.empty();
    }

    public boolean add(Node<T> root, T parent, T child) {
        checkRoot(root);

        Optional<Node<T>> parentNode = findByKey(root, parent);
        Optional<Node<T>> childNode = findByKey(root, child);

        if (parentNode.isEmpty() || childNode.isPresent()) {
            return false;
        }

        parentNode.get().getChildren().add(new Node<>(child));
        return true;
    }

    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        checkRoot(root);

        if (root.getValue().equals(key)) {
            return Optional.of(root);
        }

        for (int i = 0; i < root.getChildren().size(); i++) {
            Node<T> child = root.getChildren().get(i);
            if (child.getValue().equals(key)) {
                root.getChildren().remove(i);
                return Optional.of(child);
            }

            Optional<Node<T>> result = divideByKey(child, key);
            if (result.isPresent()) {
                return result;
            }
        }

        return Optional.empty();
    }

    private void checkRoot(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("Корневой узел не может быть null");
        }
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
        System.out.println(treeUtils.findByKey(root, "D").orElse(null));
        System.out.println(treeUtils.findByKey(root, "Z").orElse(null));
        System.out.println(treeUtils.add(root, "F", "G"));
        System.out.println(treeUtils.countNode(root));
        System.out.println(treeUtils.findAll(root));
        System.out.println(treeUtils.divideByKey(root, "C"));
        System.out.println(treeUtils.countNode(root));
        System.out.println(treeUtils.findAll(root));
    }
}