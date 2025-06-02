package ru.job4j.algo.tree.binarytree;

public class Node {

    private final int key;
    private Node left;
    private Node right;

    public Node(int key) {
        this.key = key;
    }

    public Node(int key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public String getText() {
        return String.valueOf(key);
    }

}
