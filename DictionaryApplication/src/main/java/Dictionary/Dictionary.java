package Dictionary;

import java.util.Map;
import java.util.TreeMap;

public class Dictionary {
    private Word[] words;

    protected Map<String, String> dictionary;


    public Dictionary() {
        dictionary = new TreeMap<>();
    }

    public void addWord(String target, String explain) {
        dictionary.put(target, explain);
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
