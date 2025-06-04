package ru.job4j.algo.tree.binarytree;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    public boolean put(T key) {
        boolean result;
        if (Objects.isNull(root)) {
            root = new Node(key);
            result = true;
        } else {
            result = put(root, key);
        }
        return result;
    }

    private boolean put(Node node, T key) {
        int comparison = key.compareTo(node.key);
        if (comparison < 0) {
            if (node.left == null) {
                node.left = new Node(key);
                return true;
            } else {
                return put(node.left, key);
            }
        } else if (comparison > 0) {
            if (node.right == null) {
                node.right = new Node(key);
                return true;
            } else {
                return put(node.right, key);
            }
        } else {
            return false;
        }
    }

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

    public boolean remove(T key) {
        boolean result = false;
        if (Objects.nonNull(key) && Objects.nonNull(root)) {
            result = remove(root, key);
        }
        return result;
    }

    private boolean remove(Node source, T key) {
        boolean result = true;
        Node current = source;
        Node parent = source;
        boolean isLeft = true;
        while (result && current != null && !Objects.equals(current.key, key)) {
            parent = current;
            int cmp = key.compareTo(current.key);
            isLeft = cmp < 0;
            current = isLeft ? current.left : current.right;
            if (current == null) {
                result = false;
            }
        }
        if (result) {
            assert current != null;
            Node replacement = getReplacement(current);
            swap(isLeft, source, parent, current, replacement);
            if (replacement != null) {
                replacement.left = current.left;
                if (replacement != current.right) {
                    replacement.right = current.right;
                }
            }
            clearNode(current);
        }
        return result;
    }

    private Node getReplacement(Node node) {
        if (node.left == null) {
            return node.right;
        } else if (node.right == null) {
            return node.left;
        } else {
            return getHeir(node);
        }
    }

    private void clearNode(Node node) {
        /*System.out.println("Очищается узел: " + node.key);*/
        node.key = null;
        node.left = null;
        node.right = null;
    }

    private void swap(boolean isLeft, Node source, Node parent, Node current, Node equal) {
        if (Objects.equals(current, source)) {
            root = equal;
        } else if (isLeft) {
            parent.left = equal;
        } else {
            parent.right = equal;
        }
    }

    private Node getHeir(Node deletedNode) {
        Node nodeParent = deletedNode;
        Node node = deletedNode;
        Node current = deletedNode.right;
        while (current != null) {
            nodeParent = node;
            node = current;
            current = current.left;
        }
        if (node != deletedNode.right) {
            nodeParent.left = node.right;
            node.right = deletedNode.right;
        }
        return node;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        inSymmetricalOrder(root, result);
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
        inPreOrder(root, result);
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
        inPostOrder(root, result);
        return result;
    }

    private void inPostOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inPostOrder(localRoot.left, list);
            inPostOrder(localRoot.right, list);
            list.add(localRoot.key);
        }
    }

    public T minimum() {
        return Objects.nonNull(root) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public T maximum() {
        return Objects.nonNull(root) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private T key;
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
            return key.toString();
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] array = new int[]{2, 1, 10, 6, 14, 4, 8, 12, 16, 11, 9, 13, 15, 17, 3, 5, 7};
        for (int i : array) {
            bst.put(i);
        }
        System.out.println(bst);
        System.out.println(bst.remove(10));
        System.out.println("После удаления узла 10 :");
        System.out.println(bst);
    }
}
