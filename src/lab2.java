import java.nio.file.Path;
import java.util.*;


class Node<T extends Comparable> {
    public Integer key;
    public int height;
    public Node left, right, p;


    public Node(Integer key) {
        this.key = key;
    }

}


class BST <T extends Comparable>{

    private Node<Integer> root;             // root of BST

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

    private void inorderHelper(Node<Integer> root) {
        if(root != null) {
            inorderHelper(root.left);
            System.out.println(root.key);
            inorderHelper(root.right);
        }
    }

    public void preorder() {
        preorderHelper(root);
    }

    public void preorderHelper(Node<Integer> root) {
        if(root != null) {
            System.out.println(root.key);
            preorderHelper(root.left);
            preorderHelper(root.right);
        }
    }

    public int height() {
        return heightHelper(root);
    }

    public int heightHelper(Node<Integer> root) {
        if(root == null) {
            return -1;
        }
        int leftH = heightHelper(root.left);
        int rightH = heightHelper(root.right);
        return Math.max(leftH, rightH)+1;

    }

    public Node<Integer> search(Integer key) {
        return searchHelper(root, key);
    }

    public Node<Integer> searchHelper(Node<Integer> node, Integer key) {
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



    public Node<Integer> successor(Node<Integer> node) {
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

        Node<Integer> parent = node.p;
        while(parent != null && node == parent.right) {
            node = parent;
            parent = parent.p;
        }
        return parent;
    }

    public boolean isPerfectlyBalanced() {
        return isPerfectlyBalancedHelper(root) != -2;
    }

    private int isPerfectlyBalancedHelper(Node<Integer> node) {
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

    public Node<Integer> searchClosest(Integer k) {
        Node<Integer> closest = null;
        Node<Integer> current = root;

        while(current != null) {
            if(k-current.key < 0) {
                if(closest == null || Math.abs(k-current.key) < Math.abs(k-closest.key)) {
                    closest = current;
                }
                current = current.left;
            }
            else if(k-current.key > 0) {
                if(closest == null || Math.abs(k-current.key) < Math.abs(k-closest.key)) {
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
        Stack<Node<Integer>> stack = new Stack<>();
        Node<Integer> curr = root;
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

    public void PrintPathFromTo(Node<Integer> node1, Node<Integer> node2) {
        if(root == null || node1 == null || node2 == null) {
            return;
        }

        Node<Integer> lca = lowestCommonAncestor(root, node1, node2);
        if(lca == null) {
            return;
        }

        //from node1 to lca
        while(node1 != lca) {
            System.out.print(node1.key + "->");
            node1 = node1.p;
        }

        Stack<Node<Integer>> stack = new Stack<>();
        while(node2 != lca) {
            stack.push(node2);
            node2 = node2.p;
        }
        System.out.print(lca.key);
        while(!stack.isEmpty()) {
            System.out.print("->" + stack.pop().key);
        }
    }

    private Node<Integer> lowestCommonAncestor(Node<Integer> root, Node<Integer> node1, Node<Integer> node2) {
        if(root == null || node1 == null || node2 == null) {
            return null;
        }
//        System.out.println(root.key + " " + node1.key + " " + node2.key);
        if(root.key < node1.key && root.key < node2.key) {
            return lowestCommonAncestor(root.right, node1, node2);
        }

        if(root.key > node1.key && root.key > node2.key) {
            return lowestCommonAncestor(root.left, node1, node2);
        }

        return root;


    }

    public void PrintPathsWithSum(Integer sum) {
        List<Node<Integer>> path = new ArrayList<>();
        PathsWithSumHelper(sum, root, 0, path);
    }

    public void PathsWithSumHelper(Integer sum, Node<Integer> node, Integer currSumm, List<Node<Integer>> path) {
        if(node == null) {
            return;
        }

        path.add(node);
        currSumm += node.key;

        if(sum == currSumm) {
            printPath(path);
        }

        PathsWithSumHelper(sum, node.left, currSumm, path);
        PathsWithSumHelper(sum, node.right, currSumm, path);

        path.remove(path.size()-1);currSumm -= node.key;
    }

    private void printPath(List<Node<Integer>> path) {
        for(Node<Integer> node : path) {
            System.out.print(node.key + " ");
        }
        System.out.println("\n");
    }

    public void printLevels() {
        if(root == null) {
            return;
        }

        Queue<Node<Integer>> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            for(int i = 0; i < q.size(); i++) {
                Node<Integer> node = q.poll();
                System.out.print(node.key + " ");
                if(node.left != null) {
                    q.add(node.left);
                }
                if(node.right != null) {
                    q.add(node.right);
                }
            }
        }

    }


    public static void main(String[] args) {

        BST<Integer> tree=new BST<>();
//        st1.insert(8);
//        st1.insert(15);
//        st1.insert(2);
//        st1.insert(5);
//        st1.insert(4);
//        st1.insert(10);
//        st1.insert(3);
//        st1.insert(1);
//        st1.insert(20);
//        st1.insert(18);
//        st1.insert(7);
//        st1.insert(22);
//
//        System.out.println("Is perfectly balanced: ");
//        System.out.println(st1.isPerfectlyBalanced());
//
//        System.out.println("search closest 16: ");
//        System.out.println(st1.searchClosest(16).key);
//
//        System.out.println("check exist two nodes with sum 12");
//        System.out.println(st1.CheckExistTwoNodesWithSum(12));
//
//        System.out.println("print path from 10 to 18");
//        st1.PrintPathFromTo(st1.search(10), st1.search(18));
//
//        System.out.println("\nprint path with sum 22");
//        st1.PrintPathsWithSum(22);
//
//        System.out.println("print levels");
//        st1.printLevels();

        Random random = new Random();

        long start = System.nanoTime();
        for(int i = 0; i < 1000000; i++) {
            tree.insert(i);
        }
        long end = System.nanoTime();
        System.out.println((end-start)/1000000 + "ms");

        System.out.println(tree.height());

//        st1.inorder();
//        System.out.println("\n");
//        st1.preorder();
//        System.out.println("Height: ");
//        System.out.println(st1.height());
//
//        System.out.println("Search for 7: ");
//        System.out.println(st1.search(7).key);
//
//        System.out.println("Successor of 8: ");
//        System.out.println(st1.successor(st1.search(8)).key);
//
//        System.out.println("Is perfectly balanced: ");
//        System.out.println(st1.isPerfectlyBalanced());
//
//        System.out.println("Closest of 16: ");
//        System.out.println(st1.searchClosest(16).key);
//
//        System.out.println("Do we have sum == 16: ");
//        System.out.println(st1.CheckExistTwoNodesWithSum(16));
//
//
//        System.out.println("Path from 10->22: ");
//        st1.PrintPathFromTo(st1.search(10), st1.search(22));
//
//        System.out.println("\n\nPath with sum 20: ");
//        st1.PrintPathsWithSum(20);
//
//        System.out.println("Print levels: ");
//        st1.printLevels();

    }

}
