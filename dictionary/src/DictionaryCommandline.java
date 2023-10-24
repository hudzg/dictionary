import java.util.Map;
import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement {
    public DictionaryCommandline() {
    }

    public void showAllWords() {
        clearScreen();
        for (Map.Entry word : dictionary.entrySet()) {
            System.out.println(word.getKey() + ": " + word.getValue());
            System.out.println();
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Ấn phím 0 để thoát");
        while (input.nextInt() != 0){

        }
    }

    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }

    public void displayMenu(){
        clearScreen();
        System.out.println("Welcome to My Application!\n" +
                "[0] Exit\n" +
                "[1] Add\n" +
                "[2] Remove\n" +
                "[3] Update\n" +
                "[4] Display\n" +
                "[5] Lookup\n" +
                "[6] Search\n" +
                "[7] Game\n" +
                "[8] Import from file\n" +
                "[9] Export to file\n" +
                "Your action: ");
    }

    public void dictionaryAdvanced() {
        Scanner input = new Scanner(System.in);
        while (true) {
            displayMenu();
            int type = input.nextInt();
            if (type == 0) break;
            else if (type == 1) {
                insertFromCommandline();
            } else if (type == 2) {
                removeFromCommandline();
            } else if (type == 3) {
                updateFromCommandline();
            } else if (type == 4) {
                showAllWords();
            } else if (type == 5) {
                dictionaryLookup();
            } else if (type == 6) {
                dictionarySearcher();
            } else if (type == 7) {

            } else if (type == 8) {

            } else if (type == 9) {

            } else {
                System.out.println("Action not supported");
            }
        }
    }

    public static void main(String[] args) {
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        dictionaryCommandline.insertFromFile();
//        dictionaryCommandline.showAllWords();
        dictionaryCommandline.dictionaryAdvanced();
    }
}
