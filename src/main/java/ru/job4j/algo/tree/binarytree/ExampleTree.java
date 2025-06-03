package ru.job4j.algo.tree.binarytree;

public class ExampleTree {

    private final Node4PrintTree root = new Node4PrintTree(1,
            new Node4PrintTree(2,
                    new Node4PrintTree(4,
                            new Node4PrintTree(8, new Node4PrintTree(16), new Node4PrintTree(17)),
                            new Node4PrintTree(9, new Node4PrintTree(18), new Node4PrintTree(19))),
                    new Node4PrintTree(5,
                            new Node4PrintTree(10, new Node4PrintTree(20), new Node4PrintTree(21)),
                            new Node4PrintTree(11, new Node4PrintTree(22), new Node4PrintTree(23))
                    )
            ),
            new Node4PrintTree(3,
                    new Node4PrintTree(6,
                            new Node4PrintTree(12, new Node4PrintTree(24), new Node4PrintTree(25)),
                            new Node4PrintTree(13, new Node4PrintTree(26), new Node4PrintTree(27))),
                    new Node4PrintTree(7,
                            new Node4PrintTree(14, new Node4PrintTree(28), new Node4PrintTree(29)),
                            new Node4PrintTree(15, new Node4PrintTree(30), new Node4PrintTree(31)))
            )
    );

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

}

