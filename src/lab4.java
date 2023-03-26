class Trie {

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;

        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if(current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEnd = true;
    }

    public void display() {
        display(root, "");
    }

    private void display(TrieNode node, String word) {
        if(node.isEnd) {
            System.out.println(word);
        }
        for(int i = 0; i < node.children.length; i++) {
            TrieNode child = node.children[i];
            if(child != null) {
                char ch = (char)('a'+i);
                display(child, word+ch);
            }
        }
    }

}

class TrieNode {
    TrieNode[] children;
    boolean isEnd;

    public TrieNode() {
        isEnd = false;
        children = new TrieNode[28];
    }
}

class lab4 {
    public static void main(String[] args) {
        Trie tree = new Trie();

        tree.insert("cars");
        tree.insert("car");
        tree.insert("abecedar");
        tree.insert("archbischop");

        tree.display();
    }
}