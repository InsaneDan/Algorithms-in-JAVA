package lesson6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lesson6HW {
    static int min = -100;
    static int max = 100;
    static int maxDepth = 4;
    static int treesCount = 20;
    static List<Tree<Integer>> forest = new ArrayList<>();

    public static void main(String[] args) {
        TreeImpl<Integer> tree;
        int balancedCounter = 0;

        for (int i = 0; i < treesCount; i++) {
            tree = buildTree();
            forest.add(tree);
            if (tree.isBalanced(tree.getRoot())) {
                balancedCounter++;
                tree.display();
            }
        }

        System.out.printf("Создано деревьев - %d, глубина - %d уровней, из них сбалансированных - %d (%.1f%%) \n",
                treesCount, maxDepth, balancedCounter, balancedCounter * 100.0 / treesCount);
    }

    public static TreeImpl<Integer> buildTree () {
        Random random = new Random();
        TreeImpl<Integer> tree = new TreeImpl<>();
        while (tree.depth(tree.getRoot()) < maxDepth) {
            tree.add(random.nextInt(++max - min) + min);
        }
        return tree;
    }

}
