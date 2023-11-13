package Dictionary;

import java.util.Map;
import java.util.TreeMap;

public class Dictionary {
    protected Word[] words;
    protected int count_words;
    protected Map<String, String> dictionary;


    public Dictionary() {
        words = new Word[1000010];
        dictionary = new TreeMap<>();
        count_words = 0;
    }

    public void addWord(String target, String explain) {
        dictionary.put(target, explain);
    }
    public Word getWord(int i){
        return words[i];
    }

    public void clearScreen(){
        try{

            String operatingSystem = System.getProperty("os.name"); //Check the current operating system
            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
