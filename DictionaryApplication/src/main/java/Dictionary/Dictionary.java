package Dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Dictionary {

    protected int wordSize;
    protected Word[] words;
    protected Trie trie;

    public Dictionary() {
        words = new Word[1000010];
      //  dictionary = new TreeMap<>();
        wordSize = 0;
        trie = new Trie();
    }

    public void addWord(String target, String explain) {
//        dictionary.put(target, explain);
        trie.add(target, wordSize);
        words[wordSize++] = new Word(target, explain);
    }

    public int getWordSize(){
        return wordSize;
    }

    public Word getWord(int i) {
        return words[i];
    }

    public String getTargetAt(int pos) {
        return words[pos].getWordTarget();
    }

    public String getExplainAt(int pos) {
        return words[pos].getWordExplain();
    }

    public void removeWord(String s) {
        int idx = trie.remove(s);
//        System.out.println(idx);
        if(idx != -1) words[idx] = null;
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
