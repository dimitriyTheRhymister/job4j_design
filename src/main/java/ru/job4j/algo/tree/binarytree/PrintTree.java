package ru.job4j.algo.tree.binarytree;

import java.util.ArrayList;
import java.util.List;

public class PrintTree {

    private static final int ADJUSTMENT_FOR_ODD_WIDTH = -1;
    private static final int INITIAL_PER_PIECE_MULTIPLIER = 4;

    private PrintTree() {
        throw new IllegalStateException("Utility class");
    }

    public static String getTreeDisplay(VisualNode root) {
        if (root == null) {
            return "";
        }

        List<List<String>> lines = buildTreeLines(root);
        int initialPieceWidth = calculateInitialPieceWidth(lines);

        StringBuilder buffer = new StringBuilder();
        int pieceWidth = initialPieceWidth;

        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int halfPieceWidth = (int) Math.floor(pieceWidth / 2f) - 1;

            if (i > 0) {
                printConnectionLine(buffer, line, halfPieceWidth, pieceWidth);
            }

            printNodeLine(buffer, line, pieceWidth);
            buffer.append('\n');
            pieceWidth /= 2;
        }

        return buffer.toString();
    }

    private static List<List<String>> buildTreeLines(VisualNode root) {
        List<List<String>> lines = new ArrayList<>();
        List<VisualNode> level = new ArrayList<>();
        List<VisualNode> next = new ArrayList<>();

        level.add(root);
        int nodeCount = 1;

        while (nodeCount != 0) {
            nodeCount = 0;
            List<String> line = new ArrayList<>();

            for (VisualNode node : level) {
                if (node == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    line.add(node.getText());
                    next.add(node.getLeft());
                    if (node.getLeft() != null) {
                        nodeCount++;
                    }

                    next.add(node.getRight());
                    if (node.getRight() != null) {
                        nodeCount++;
                    }
                }
            }

            lines.add(line);
            List<VisualNode> temp = level;
            level = next;
            next = temp;
            next.clear();
        }

        return lines;
    }

    private static int calculateInitialPieceWidth(List<List<String>> lines) {
        int maxLength = 0;
        for (String text : lines.getFirst()) {
            maxLength = Math.max(maxLength, text.length());
        }

        if (maxLength % 2 == 1) {
            maxLength += ADJUSTMENT_FOR_ODD_WIDTH;
        }

        return lines.getLast().size() * (maxLength + INITIAL_PER_PIECE_MULTIPLIER);
    }

    private static void printConnectionLine(StringBuilder buffer, List<String> line, int hpw, int perPiece) {
        for (int j = 0; j < line.size(); j++) {
            char symbol = ' ';
            if (j % 2 == 1) {
                if (line.get(j - 1) != null) {
                    symbol = (line.get(j) != null) ? ' ' : '/';
                } else if (line.get(j) != null) {
                    symbol = '\\';
                }
            }
            buffer.append(symbol);

            if (line.get(j) == null) {
                buffer.append(" ".repeat(Math.max(0, perPiece - 1)));
            } else {
                buffer.append(String.valueOf(j % 2 == 0 ? " " : ' ').repeat(Math.max(0, hpw)));
                buffer.append(j % 2 == 0 ? '/' : '\\');
                buffer.append(String.valueOf(j % 2 == 0 ? ' ' : " ").repeat(Math.max(0, hpw)));
            }
        }
        buffer.append('\n');
    }

    private static void printNodeLine(StringBuilder buffer, List<String> line, int perPiece) {
        for (String word : line) {
            if (word == null) {
                word = "";
            }
            double space = perPiece / 2f - word.length() / 2f;
            buffer.append(" ".repeat(Math.max(0, (int) Math.ceil(space))))
                    .append(word)
                    .append(" ".repeat(Math.max(0, (int) Math.floor(space))));
        }
    }
}