package Dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Dictionary {
    private class Node {
        private Node parent;
        private Node[] child;
        private List<Integer> pos;

        public Node() {
            child = new Node[26];
            pos = new ArrayList<>();
        }
    }

    protected int wordSize;
    protected Word[] words;

    protected Map<String, String> dictionary;
    private Node root;

    public Dictionary() {
        words = new Word[1000000];
        dictionary = new TreeMap<>();
        root = new Node();
        wordSize = 0;
    }

    public void addWord(String target, String explain) {
        dictionary.put(target, explain);
        Node node = root;
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) < 'a' || target.charAt(i) > 'z') continue;
            int c = target.charAt(i) - 'a';
            if (node.child[c] == null) {
                node.child[c] = new Node();
                node.child[c].parent = node;
            }
            node = node.child[c];
            node.pos.add(wordSize);
        }
        words[wordSize++] = new Word(target, explain);
    }

    public List<Integer> getWord(String s) {
        Node node = root;
        for(int i = 0; i < s.length(); i++){
            if (s.charAt(i) < 'a' || s.charAt(i) > 'z') continue;
            int c = s.charAt(i) - 'a';
            if (node.child[c] == null) return new ArrayList<>();
            node = node.child[c];
        }
        return node.pos;
    }

    public String getTargetAt(int pos) {
        return words[pos].getWordTarget();
    }

    public String getExplainAt(int pos) {
        return words[pos].getWordExplain();
    }

    public void clearScreen() {
        try {

            String operatingSystem = System.getProperty("os.name"); //Check the current operating system
            if (operatingSystem.contains("Windows")) {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
