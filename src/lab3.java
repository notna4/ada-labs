

class AVLTree {
    Node root;
    public AVLTree() {
        root=null;
    }

    int height(Node root) {
        if(root == null) {
            return 0;
        }
        return root.height;
    }

    private int getBalance(Node root) {
        if(root == null) {
            return 0;
        }
        return height(root.left)-height(root.right);
    }

    public void insert(int key) {
        insertHelper(root, key);
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

    private void insertHelper(Node root, int key) {
        if(root == null) {
            new Node(key);
            return;
        }

        if(key > root.key) {
            insertHelper(root.right, key);
        }
        else if(key < root.key) {
            insertHelper(root.left, key);
        }

        root.height = 1+Math.max(height(root.left), height(root.right));
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && key < root.left.key)
            rightRotate(root);

        // Right Right Case
        if (balance < -1 && key > root.right.key)
            leftRotate(root);

        // Left Right Case
        if (balance > 1 && key > root.left.key) {
            root.left = leftRotate(root.left);
            rightRotate(root);
        }

        // Right Left Case
        if (balance < -1 && key < root.right.key) {
            root.right = rightRotate(root.right);
            leftRotate(root);
        }

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

        tree.insert(4);
        tree.insert(23);
        tree.insert(11);
        tree.insert(89);
        tree.insert(34);
        tree.insert(2);
        tree.insert(7);
        tree.insert(14);
        tree.insert(75);
        tree.insert(69);
        tree.insert(99);
        tree.insert(80);

        tree.inorder();
    }
}
