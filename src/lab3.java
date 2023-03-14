import java.util.Random;
import java.util.TreeMap;

class AVLTree {
    Node root;
    public AVLTree() {
        root=null;
    }

    public int height(Node node) {
        if (node == null) return 0;
        return node.height;
    }

    private int getBalance(Node root) {
        if(root == null) {
            return 0;
        }
        return height(root.left)-height(root.right);
    }

    public void insert(int key) {
        root = insertHelper(root, key);
    }

    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    private Node insertHelper(Node node, int key) {
        if (node == null) return new Node(key);

        if (key < node.key) {
            node.left = insertHelper(node.left, key);
        } else if (key > node.key) {
            node.right = insertHelper(node.right, key);
        } else {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;

    }

    public void inorder() {
        inorderHelper(root);
    }

    private void inorderHelper(Node<Integer> root) {
        if(root != null) {
            inorderHelper(root.left);
            System.out.println(root.key);
            inorderHelper(root.right);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

//        tree.insert(4);
//        tree.insert(23);
//        tree.insert(11);
//        tree.insert(89);
//        tree.insert(34);
//        tree.insert(2);
//        tree.insert(7);
//        tree.insert(14);
//        tree.insert(75);
//        tree.insert(69);
//        tree.insert(99);
//        tree.insert(80);

//        Random random = new Random();
//
//        long start = System.nanoTime();
//        for(int i = 0; i < 1000000; i++) {
//            tree.insert(random.nextInt(1000));
//        }
//        long end = System.nanoTime();
//        System.out.println((end-start)/1000000 + "ms");
//
//        System.out.println(tree.height(tree.root));


        TreeMap<Integer, Integer> tm = new TreeMap<>();

        Random random = new Random();

        long start = System.nanoTime();
        for(int i = 0; i < 4; i++) {
            tree.insert(i);
        }
        long end = System.nanoTime();
        System.out.println((end-start)/1000000 + "ms");

        System.out.println(tree.height(tree.root));


    }
}
