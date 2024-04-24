package tree.binaryTree;
import java.util.*;

public class BinaryTree<T extends Comparable<T>> extends tree.tree{
    private static class Node<T> {
        T data;
        Node<T> lchild;
        Node<T> rchild;
        public Node(T data) {
            this.data = data;
            this.lchild = null;
            this.rchild = null;
        }
    }
    private Node<T> root;

    //Using hard coded values to populate the tree - node values are entered by the programmer.
    public void insert(T data) {
        if (data == null)
            throw new IllegalArgumentException("Data cannot be null");

        if (this.root == null) {
            this.root = new Node<T>(data);
        } else {
            insert(this.root, data);
        }
    }
    private void insert(Node<T> root, T data) {
        if (root.data.compareTo(data) > 0) {
            if (root.lchild == null) {
                root.lchild = new Node<T>(data);
            } else {
                insert(root.lchild, data);
            }
        } else {
            if (root.rchild == null) {
                root.rchild = new Node<T>(data);
            } else {
                insert(root.rchild, data);
            }
        }
    }

    //Using scanner to populate the tree - node values are entered by the user.
    public void populate(Scanner scanner) {
        if (scanner == null)
            throw new IllegalArgumentException("Scanner cannot be null");

        System.out.println("Enter the root element: ");
        String input = scanner.next();
        T data = parseInput(input);
        this.root = new Node<>(data);
        populate(scanner, root);
    }
    private void populate(Scanner scanner, Node<T> node) {
        System.out.println("Enter the left child of " + node.data + " (or type 'null' to skip): ");
        String leftInput = scanner.next();
        if (!leftInput.equalsIgnoreCase("null")) {
            T leftData = parseInput(leftInput);
            node.lchild = new Node<>(leftData);
            populate(scanner, node.lchild);
        }

        System.out.println("Enter the right child of " + node.data + " (or type 'null' to skip): ");
        String rightInput = scanner.next();
        if (!rightInput.equalsIgnoreCase("null")) {
            T rightData = parseInput(rightInput);
            node.rchild = new Node<>(rightData);
            populate(scanner, node.rchild);
        }
    }
    private T parseInput(String input) {
        return (T) input;
    }

    //Display the tree
    public void displayTree() {
        displayTree(root, 0);
    }
    private void displayTree(Node<T> node, int level) {
        if (node == null) {
            return;
        }
        displayTree(node.rchild, level + 1);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.println("|\t\t");
            }
            System.out.println("|------->" + node.data);
        }
        else {
            System.out.println(node.data);
        }
        displayTree(node.lchild, level + 1);
    }

    //Preorder traversal
    @Override
    public void preorder() {
        System.out.println("Preorder traversal: ");
        preorder(root);
        System.out.println();
    }
    private void preorder(Node<T> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preorder(node.lchild);
        preorder(node.rchild);
    }

    //Inorder traversal
    @Override
    public void inorder() {
        System.out.println("Inorder traversal: ");
        inorder(root);
        System.out.println();
    }
    private void inorder(Node<T> node) {
        if (node == null) {
            return;
        }
        inorder(node.lchild);
        System.out.print(node.data + " ");
        inorder(node.rchild);
    }

    //Postorder traversal
    @Override
    public void postorder() {
        System.out.println("Postorder traversal: ");
        postorder(root);
        System.out.println();
    }
    private void postorder(Node<T> node) {
        if (node == null) {
            return;
        }
        postorder(node.lchild);
        postorder(node.rchild);
        System.out.print(node.data + " ");
    }

    //Level order traversal
//    public void levelOrder() {
//        System.out.println("Level order traversal: ");
//        levelOrder(root);
//        System.out.println();
//    }
//    private void levelOrder(Node<T> node) {
//        if (node == null) {
//            return;
//        }
//        Queue<Node<T>> queue = new LinkedList<>();
//        queue.add(node);
//        while (!queue.isEmpty()) {
//            Node<T> temp = queue.poll();
//            System.out.print(temp.data + " ");
//            if (temp.lchild != null) {
//                queue.add(temp.lchild);
//            }
//            if (temp.rchild != null) {
//                queue.add(temp.rchild);
//            }
//        }
//    }

    //Height of the tree
    public void height() {
        System.out.println("Height of the tree: " + height(root));
    }
    private int height(Node<T> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = height(node.lchild);
        int rightHeight = height(node.rchild);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    //Level order traversal
    @Override
    public void levelOrder() {
        levelOrder(root);
    }
    private void levelOrder (Node<T> node) {
        int i, height = height(node);
        System.out.println("Level order traversal: ");
        for (i = 0; i < height; i++) {
            System.out.printf("Level [%d]: ", i + 1);
            levelOrder(node, i);
            System.out.println();
        }
        System.out.println();
    }
    private void levelOrder (Node<T> node, int level) {
        if (node == null)
            return;

        if (level == 0)
            System.out.print(node.data + " ");
        else {
            levelOrder(node.lchild, level - 1);
            levelOrder(node.rchild, level - 1);
        }
    }

    //Count the number of nodes in the tree
    public void countNodes() {
        System.out.println("Number of nodes in the tree: " + countNodes(root));
    }
    private int countNodes(Node<T> node) {
        if (node == null)
            return 0;
        return 1 + countNodes(node.lchild) + countNodes(node.rchild);
    }

    //count the number of leaf nodes in the tree and display the nodes
    public void countLeafNodes() {
        System.out.println("Number of leaf nodes in the tree: " + countLeafNodes(root));
    }
    private int countLeafNodes(Node<T> node) {
        if (node == null)
            return 0;
        if (node.lchild == null && node.rchild == null) {
            System.out.print(node.data + " ");
            return 1;
        }
        return countLeafNodes(node.lchild) + countLeafNodes(node.rchild);
    }

    //count the number of nodes with only one child and display the nodes
    public void countNodesWithOneChild() {
        System.out.println("Number of nodes with only one child: " + countNodesWithOneChild(root));
    }
    private int countNodesWithOneChild(Node<T> node) {
        if (node == null)
            return 0;
        if ((node.lchild == null && node.rchild != null) || (node.lchild != null && node.rchild == null)) {
            System.out.print(node.data + " ");
            return 1;
        }
        return countNodesWithOneChild(node.lchild) + countNodesWithOneChild(node.rchild);
    }



}
