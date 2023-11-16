package Dictionary;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private class Node {
        private Node[] child;
        private List<Integer> pos;
        private int endAt;

        public Node() {
            child = new Node[26];
            pos = new ArrayList<>();
            endAt = -1;
        }
    }

    private Node root;

    public Trie() {
        root = new Node();
    }

    public void add(String s, int value) {
        remove(s);
        Node node = root;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 'a' || s.charAt(i) > 'z') continue;
            int c = s.charAt(i) - 'a';
            if (node.child[c] == null) {
                node.child[c] = new Node();
            }
            node = node.child[c];
            node.pos.add(value);
        }
        node.endAt = value;
    }

    public List<Integer> get(String s) {
        Node node = root;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 'a' || s.charAt(i) > 'z') continue;
            int c = s.charAt(i) - 'a';
            if (node.child[c] == null) return new ArrayList<>();
            node = node.child[c];
        }
        return node.pos;
    }

    public int getExactly(String s) {
        Node node = root;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 'a' || s.charAt(i) > 'z') continue;
            int c = s.charAt(i) - 'a';
            if (node.child[c] == null) return -1;
            node = node.child[c];
        }
        return node.endAt;
    }

    public int remove(String s) {
        Node node = root;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 'a' || s.charAt(i) > 'z') continue;
            int c = s.charAt(i) - 'a';
            if (node.child[c] == null) return -1;
            node = node.child[c];
        }
        int value = node.endAt;
        if (value == -1) return -1;
        node.endAt = -1;
        node = root;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 'a' || s.charAt(i) > 'z') continue;
            int c = s.charAt(i) - 'a';
            if (node.child[c] == null) return -1;
            node = node.child[c];
            node.pos.remove(Integer.valueOf(value));
        }
        return value;
    }
}
