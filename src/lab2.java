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



    public static void main(String[] args) {

        BST<Integer> st1=new BST<Integer>();
        st1.insert(5);
        st1.insert(2);
        st1.insert(10);
        st1.insert(8);
        st1.insert(15);
        st1.inorder();

        BST<String> st2=new BST<String>();
        st2.insert("dog");
        st2.insert("bear");
        st2.insert("cat");
        st2.insert("fish");
        st2.insert("wolf");
//        st2.inorder();

    }

}
