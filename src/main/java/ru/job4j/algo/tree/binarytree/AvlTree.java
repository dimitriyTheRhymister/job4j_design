package ru.job4j.algo.tree.binarytree;

import java.util.*;

public class AvlTree<T extends Comparable<T>> {

    private Node root;

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    private Node find(Node node, T key) {
        if (node == null || node.key.compareTo(key) == 0) {
            return node;
        }
        if (node.key.compareTo(key) > 0) {
            return find(node.left, key);
        } else {
            return find(node.right, key);
        }
    }

    public boolean insert(T value) {
        boolean result = false;
        if (Objects.nonNull(value) && !contains(value)) {
            root = insert(root, value);
            result = true;
        }
        return result;
    }

    private Node insert(Node node, T value) {
        if (Objects.isNull(node)) {
            return new Node(value);
        }
        int comparisonResult = value.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }
        updateHeight(node);
        return balance(node);
    }

    public boolean remove(T element) {
        boolean result = false;
        if (Objects.nonNull(element) && Objects.nonNull(root) && contains(element)) {
            root = remove(root, element);
            result = true;
        }
        return result;
    }

    private Node remove(Node node, T element) {
        if (node == null) {
            return null;
        }
        int comparisonResult = element.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = remove(node.left, element);
        } else if (comparisonResult > 0) {
            node.right = remove(node.right, element);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    Node maxNode = maximum(node.left);
                    if (Objects.nonNull(maxNode)) {
                        T heir = maxNode.key;
                        node.key = heir;
                        node.left = remove(node.left, heir);
                    }
                } else {
                    Node minNode = minimum(node.right);
                    if (Objects.nonNull(minNode)) {
                        T heir = minNode.key;
                        node.key = heir;
                        node.right = remove(node.right, heir);
                    }
                }
            }
        }
        updateHeight(node);
        return balance(node);
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightNodeHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            if (node.left.balanceFactor >= 0) {
                result = leftRightCase(node);
            } else {
                result = rightRotation(node);
            }
        } else if (node.balanceFactor > 1) {
            if (node.right.balanceFactor >= 0) {
                result = leftRotation(node);
            } else {
                result = rightLeftCase(node);
            }
        }
        return result;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    private Node rightRotation(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    public T minimum() {
        Node minNode = minimum(root);
        return Objects.nonNull(minNode) ? minNode.key : null;
    }

    private Node minimum(Node node) {
        if (Objects.isNull(node)) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public T maximum() {
        Node maxNode = maximum(root);
        return Objects.nonNull(maxNode) ? maxNode.key : null;
    }

    private Node maximum(Node node) {
        if (Objects.isNull(node)) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        if (Objects.nonNull(root)) {
            inSymmetricalOrder(root, result);
        }
        return result;
    }

    private void inSymmetricalOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot.key);
            inSymmetricalOrder(localRoot.right, list);
        }
    }

    public List<T> inPreOrder() {
        List<T> result = new ArrayList<>();
        if (Objects.nonNull(root)) {
            inPreOrder(root, result);
        }
        return result;
    }

    private void inPreOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            list.add(localRoot.key);
            inPreOrder(localRoot.left, list);
            inPreOrder(localRoot.right, list);
        }
    }

    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        if (Objects.nonNull(root)) {
            inPostOrder(root, result);
        }
        return result;
    }

    private void inPostOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inPostOrder(localRoot.left, list);
            inPostOrder(localRoot.right, list);
            list.add(localRoot.key);
        }
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(node.left) && isBalanced(node.right);
    }

    private int getHeight(Node node) {
        return node == null ? -1 : node.height;
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private int balanceFactor;
        private T key;
        private int height;
        private Node left;
        private Node right;

        public Node(T key) {
            this.key = key;
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
}
