package ru.job4j.algo.tree.binarytree;

public class Node4PrintTree implements VisualNode {

    private final int key;
    private Node4PrintTree left;
    private Node4PrintTree right;

    public Node4PrintTree(int key) {
        this.key = key;
    }

    public Node4PrintTree(int key, Node4PrintTree left, Node4PrintTree right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    @Override
    public VisualNode getLeft() {
        return left;
    }

    @Override
    public VisualNode getRight() {
        return right;
    }

    @Override
    public String getText() {
        return String.valueOf(key);
    }

}
