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

    //using hardcoded values to populate the tree - node values are entered by the programmer.



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
        System.out.println(" : Number of leaf nodes in the tree: " + countLeafNodes(root));
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
        System.out.println(" : Number of nodes with only one child: " + countNodesWithOneChild(root));
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

    //count the number of nodes with two children and display the nodes
    public void countNodesWithTwoChildren() {
        System.out.println(" : Number of nodes with two children: " + countNodesWithTwoChildren(root));
    }
    private int countNodesWithTwoChildren(Node<T> node) {
        if (node == null)
            return 0;
        if (node.lchild != null && node.rchild != null) {
            System.out.print(node.data + " ");
            return 1;
        }
        return countNodesWithTwoChildren(node.lchild) + countNodesWithTwoChildren(node.rchild);
    }

    //count the number of nodes at given level in the tree and diaplay the nodes
    public void countNodesAtGivenLevel(int level) {
        System.out.println("(Considering root as level 0)");
        System.out.println(" : Number of nodes at level " + level + ": " + countNodesAtGivenLevel(root, level));
    }
    private int countNodesAtGivenLevel(Node<T> node, int level) {
        if (node == null)
            return 0;
        if (level == 0) {
            System.out.print(node.data + " ");
            return 1;
        }
        return countNodesAtGivenLevel(node.lchild, level - 1) + countNodesAtGivenLevel(node.rchild, level - 1);
    }

    // Search for a node in the tree
    public void search(String data) {
        System.out.println("Searching for node with data: " + data);
        if (search(root, parseInput(data))) {
            System.out.println("Node found");
        } else {
            System.out.println("Node not found");
        }
    }
    private boolean search(Node<T> node, T data) {
        if (node == null)
            return false;
        if (node.data.equals(data))
            return true;
        return search(node.lchild, data) || search(node.rchild, data);
    }

    //Longest path in the tree from root to leaf
    public void longestPath() {
        System.out.println("Longest path in the tree from root to leaf: ");
        List<T> path = new ArrayList<>();
        List<T> longestPath = longestPath(root, path);
        for (T data : longestPath) {
            System.out.print(data + " ");
        }
        System.out.println();
    }
    private List<T> longestPath(Node<T> node, List<T> path) {
        if (node == null)
            return new ArrayList<>();
        path.add(node.data);
        if (node.lchild == null && node.rchild == null) {
            return path;
        }
        List<T> leftPath = longestPath(node.lchild, new ArrayList<>(path));
        List<T> rightPath = longestPath(node.rchild, new ArrayList<>(path));
        return leftPath.size() > rightPath.size() ? leftPath : rightPath;
    }

    //Delete a node from the tree
    public void delete(String data) {
        System.out.println("Deleting node with data: " + data);
        root = delete(root, parseInput(data));
    }
    private Node<T> delete(Node<T> node, T data) {
        if (node == null)
            return null;
        if (node.data.compareTo(data) > 0) {
            node.lchild = delete(node.lchild, data);
        } else if (node.data.compareTo(data) < 0) {
            node.rchild = delete(node.rchild, data);
        } else {
            if (node.lchild == null) {
                return node.rchild;
            } else if (node.rchild == null) {
                return node.lchild;
            } else {
                node.data = findMin(node.rchild);
                node.rchild = delete(node.rchild, node.data);
            }
        }
        return node;
    }
    private T findMin(Node<T> node) {
        while (node.lchild != null) {
            node = node.lchild;
        }
        return node.data;
    }

    //Mirror the tree
    public void mirror() {
        System.out.println("Mirroring the tree: ");
        mirror(root);
        System.out.println();
    }
    private void mirror(Node<T> node) {
        if (node == null)
            return;
        Node<T> temp = node.lchild;
        node.lchild = node.rchild;
        node.rchild = temp;
        mirror(node.lchild);
        mirror(node.rchild);
    }
}
