package Dictionary;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private class Node {
        private Node parent;
        private Node[] child;
        private List<Integer> pos;

        public Node() {
            child = new Node[26];
            pos = new ArrayList<>();
        }
    }

    private Node root;

    public Trie() {
        root = new Node();
    }

    public void add(String s, int value) {
        Node node = root;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 'a' || s.charAt(i) > 'z') continue;
            int c = s.charAt(i) - 'a';
            if (node.child[c] == null) {
                node.child[c] = new Node();
                node.child[c].parent = node;
            }
            node = node.child[c];
            node.pos.add(value);
        }
    }

    public List<Integer> get(String s) {
        Node node = root;
        for(int i = 0; i < s.length(); i++){
            if (s.charAt(i) < 'a' || s.charAt(i) > 'z') continue;
            int c = s.charAt(i) - 'a';
            if (node.child[c] == null) return new ArrayList<>();
            node = node.child[c];
        }
        return node.pos;
    }
}
