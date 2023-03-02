class Node<T extends Comparable> {
    public T key;

    public Node left, right, p;

    public Node(T key) {
        this.key = key;
    }
}


class BST <T extends Comparable>{

    private Node<T> root;             // root of BST

    public Node<T> getRoot() {
        return root;
    }

    public BST() {
        root=null;
    }


    public void insert(T k) {
        Node x, y;
        Node z= new Node(k);
        y=null;
        x=root;
        while (x!=null) {
            y=x;
            if (x.key.compareTo(z.key)<0)
                x=x.right;
            else
                x=x.left;
        }
        z.p=y;
        if (y==null)
            root=z;
        else
        if (y.key.compareTo(z.key)<0)
            y.right=z;
        else
            y.left=z;

    }

    public void inorder() {
        inorderHelper(root);
    }

    private void inorderHelper(Node<T> root) {
        if(root != null) {
            inorderHelper(root.left);
            System.out.println(root.key);
            inorderHelper(root.right);
        }
    }

    public void preorder() {
        preorderHelper(root);
    }

    public void preorderHelper(Node<T> root) {
        if(root != null) {
            System.out.println(root.key);
            preorderHelper(root.left);
            preorderHelper(root.right);
        }
    }

    public int height() {
        return heightHelper(root);
    }

    public int heightHelper(Node<T> root) {
        if(root == null) {
            return -1;
        }
        int leftH = heightHelper(root.left);
        int rightH = heightHelper(root.right);
        return Math.max(leftH, rightH)+1;

    }

    public Node<T> search(T key) {
        return searchHelper(root, key);
    }

    public Node<T> searchHelper(Node<T> node, T key) {
        if(node == null || node.key.equals(key)) {
            return node;
        }

        if(node.key.compareTo(key) > 0) {
            return searchHelper(node.left, key);
        }
        else {
            return searchHelper(node.right, key);
        }
    }

//    public Node<T> successor(Node<T> node) {
//        if(node == null) {
//            return null;
//        }
//
//        node = node.right;
//        while(node != null) {
//            node = node.left;
//        }
//
//        return node;
//    }

    public Node<T> successor(Node<T> node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
//        Node<T> parent = node.p;
//        while (parent != null && node == parent.right) {
//            node = parent;
//            parent = parent.p;
//        }
//        return parent;
    }

    private Node<T> leftmost(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }



    public static void main(String[] args) {

        BST<Integer> st1=new BST<Integer>();
        st1.insert(8);
        st1.insert(2);
        st1.insert(10);
        st1.insert(18);
        st1.insert(1);
        st1.insert(4);
        st1.insert(7);
        st1.insert(3);
        st1.insert(22);
        st1.insert(20);
        st1.insert(15);
        st1.inorder();
        System.out.println("\n");
        st1.preorder();
        System.out.println("Height: ");
        System.out.println(st1.height());

        System.out.println("Search for 7: ");
        System.out.println(st1.search(7));

        System.out.println("Successor of 7: ");
        System.out.println(st1.successor(st1.search(7)).key);



        BST<String> st2=new BST<String>();
        st2.insert("dog");
        st2.insert("bear");
        st2.insert("cat");
        st2.insert("fish");
        st2.insert("wolf");
//        st2.inorder();
//        st2.preorder();
    }

}
