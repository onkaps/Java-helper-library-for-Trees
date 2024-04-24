import tree.binaryTree.BinaryTree;

import java.lang.*;
import java.util.*;

public class Main {
    public static void main (String[] args) {
         BinaryTree<Integer> intBT = new BinaryTree<>();
         Scanner sc = new Scanner(System.in);
         intBT.populate(sc);
         intBT.displayTree();
         intBT.preorder();
         intBT.inorder();
         intBT.postorder();
         intBT.height();
         intBT.levelOrder();
         intBT.countNodes();
         intBT.countLeafNodes();
         intBT.countNodesWithOneChild();
         intBT.countNodesWithTwoChildren();
         intBT.countNodesAtGivenLevel(1);
         intBT.search(10);
         intBT.search(69);


    }
}

