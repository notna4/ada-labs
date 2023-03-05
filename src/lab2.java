import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


class Node<T extends Comparable> {
    public Integer key;

    public Node left, right, p;

    public Node(Integer key) {
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


    public void insert(Integer k) {
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

    public Node<T> search(Integer key) {
        return searchHelper(root, key);
    }

    public Node<T> searchHelper(Node<T> node, Integer key) {
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



    public Node<T> successor(Node<T> node) {
        if (node == null) {
            return null;
        }

        if(node.right != null) {
            node = node.right;
            while(node.left != null) {
                node = node.left;
            }
            return node;
        }

        Node<T> parent = node.p;
        while(parent != null && node == parent.right) {
            node = parent;
            parent = parent.p;
        }
        return parent;
    }

    public boolean isPerfectlyBalanced() {
        return isPerfectlyBalancedHelper(root) != -2;
    }

    private int isPerfectlyBalancedHelper(Node<T> node) {
        if(node == null) {
            return 0;
        }

        int left = isPerfectlyBalancedHelper(node.left);
        if(left == -2) {
            return -2;
        }

        int right = isPerfectlyBalancedHelper(node.right);
        if(right == -2) {
            return -2;
        }

        if(Math.abs(left-right) > 1) {
            return -2;
        }

        return left+right+1;

    }

    public Node<T> searchClosest(Integer k) {
        Node<T> closest = null;
        Node<T> current = root;

        while(current != null) {
            if(k.compareTo(current.key) < 0) {
                if(closest == null || Math.abs(k.compareTo(current.key)) < Math.abs(k.compareTo(closest.key))) {
                    closest = current;
                }
                current = current.left;
            }
            else if(k.compareTo(current.key) > 0) {
                if(closest == null || Math.abs(k.compareTo(current.key)) < Math.abs(k.compareTo(closest.key))) {
                    closest = current;
                }
                current = current.right;
            }
            else {
                return current;
            }
        }
        return closest;
    }

    public boolean CheckExistTwoNodesWithSum(Integer s) {
        if (root == null) {
            return false;
        }

        Set<Integer> set = new HashSet<>();
        Stack<Node<T>> stack = new Stack<>();
        Node<T> curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            Integer target = s - curr.key;
            if(set.contains(target)) {
                return true;
            }
            set.add(curr.key);
            curr = curr.right;
        }
        return false;
    }


    public static void main(String[] args) {

        BST<Integer> st1=new BST<>();
        st1.insert(1);
        st1.insert(2);
        st1.insert(8);
        st1.insert(5);
        st1.insert(4);
        st1.insert(3);
        st1.insert(7);
        st1.insert(15);
        st1.insert(10);
        st1.insert(20);
        st1.insert(18);
        st1.insert(22);
        st1.inorder();
        System.out.println("\n");
        st1.preorder();
        System.out.println("Height: ");
        System.out.println(st1.height());

        System.out.println("Search for 7: ");
        System.out.println(st1.search(7));

        System.out.println("Successor of 18: ");
        System.out.println(st1.successor(st1.search(18)).key);

        System.out.println("Is perfectly balanced: ");
        System.out.println(st1.isPerfectlyBalanced());

        System.out.println("Closest of 16: ");
        System.out.println(st1.searchClosest(16).key);

        System.out.println("Do we have sum == 16: ");
        System.out.println(st1.CheckExistTwoNodesWithSum(16));


    }

}
