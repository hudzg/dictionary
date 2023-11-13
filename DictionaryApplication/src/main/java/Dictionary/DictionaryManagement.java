package Dictionary;

//import java.awt.image.ImageProducer;

import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DictionaryManagement extends Dictionary {
    public DictionaryManagement() {
        super();

    }
    public void changeMeaning(String words, String Meaning){
        if(dictionary.containsKey(words))  dictionary.put(words, Meaning);
    }

    public void insertFromCommandline() {
        clearScreen();
        Scanner input = new Scanner(System.in);
        System.out.println("Bạn nhập số từ đê!!!");
        int n = input.nextInt();
        input.nextLine();
        System.out.println("Bạn nhập các từ đê!!!");
        while (n-- > 0) {
            String target = input.nextLine();
            String explain = input.nextLine();
            dictionary.put(target, explain);
        }
        System.out.println("Ấn phím 0 để thoát");
        while (input.nextInt() != 0) {

        }
    }

    public void removeFromCommandline() {
        clearScreen();
        Scanner input = new Scanner(System.in);
        System.out.println("Bạn nhập từ cần xoá đê!!!");
        String s = input.nextLine();
        dictionary.remove(s);
        System.out.println("Đã xoá thành công!!!");
        System.out.println("Ấn phím 0 để thoát");
        while (input.nextInt() != 0) {

        }
    }

    public void updateFromCommandline() {
        clearScreen();
        Scanner input = new Scanner(System.in);
        System.out.println("Bạn nhập từ cần sửa đê!!!");
        String s = input.nextLine();
        System.out.println("Bạn nhập nghĩa mới đê!!!");
        String t = input.nextLine();
        dictionary.replace(s, t);
        System.out.println("Đã sửa thành công!!!");
        System.out.println("Ấn phím 0 để thoát");
        while (input.nextInt() != 0) {

        }
    }

    public void dictionaryLookup() {
        clearScreen();
        Scanner input = new Scanner(System.in);
        System.out.println("Bạn nhập từ cần tra cứu đê!!!");
        String s = input.nextLine();
        if (!dictionary.containsKey(s)) {
            System.out.println("Không tìm thấy :((");
        } else {
            System.out.println("Từ: " + s);
            System.out.println("Nghĩa: " + dictionary.get(s));
        }
        System.out.println("Ấn phím 0 để thoát");
        while (input.nextInt() != 0) {

        }
    }

    public String dictionaryLookup(String s) {
        return dictionary.get(s);
    }

    public void dictionarySearcherCommandline() {
        clearScreen();
        Scanner input = new Scanner(System.in);
        System.out.println("Bạn nhập từ cần tra cứu đê!!!");
        String sample = input.nextLine();
        int len = sample.length();

        int cnt = 0;
        for (String ii : dictionary.keySet()) {
            if (ii.length() >= len && ii.substring(0, len).equals(sample)) {
                System.out.print(ii + ", ");
                if (++cnt % 10 == 0) System.out.println("");
            }
        }
        System.out.println("Ấn phím 0 để thoát");
        while (input.nextInt() != 0) {

        }
    }

    public List<String> dictionarySearcher(String sample) {
        int len = sample.length();

        List<String> stringList = new ArrayList<>();
        for (String ii : dictionary.keySet()) {
            if (ii.length() >= len && ii.substring(0, len).equals(sample)) {
                stringList.add(ii);
            }
        }
        return stringList;

    }

    public void dictionaryExportToFile() {

    }

    public void Game() {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);

        int cnt = 0;
        List<String> wrds = new ArrayList<>();
        for (String ii : dictionary.keySet()) {
            if (++cnt > 1000) break;
            wrds.add(ii);
        }
        String sample2 = "ueoaibcdfghxyzlmnopqw";
        String sample3 = "ABCD";
        Map<Integer, String> M = new TreeMap<>();
        M.put(0, "A");
        M.put(1, "B");
        M.put(2, "C");
        M.put(3, "D");
        do {
            int id = Math.abs(rand.nextInt()) % wrds.size();
            String word = wrds.get(id);
            int len = word.length();

            int missed = Math.abs(rand.nextInt()) % len;
            String ans = word.substring(missed, missed + 1);
            String sample4 = "";
            while (sample4.length() < 4) {
                int x = Math.abs(rand.nextInt()) % sample2.length();
                boolean ok = !sample2.substring(x, x + 1).equals(ans);
                for (int i = 0; i < sample4.length(); ++i) {
                    if (sample2.substring(x, x + 1).equals(sample4.substring(i, i + 1))) ok = false;
                }
                if (ok) sample4 += sample2.substring(x, x + 1);
            }

            for (int i = 0; i < len; ++i) {
                if (i == missed) System.out.print("_");
                else System.out.print(word.substring(i, i + 1));
            }
            System.out.println("");

            int ans_id = Math.abs(rand.nextInt()) % 4;
            for (int i = 0; i < 4; ++i) {
                System.out.print(sample3.substring(i, i + 1) + ": ");
                if (i == ans_id) System.out.println(ans);
                else System.out.println(sample4.substring(i, i + 1));
            }
            System.out.print("Your choice [A/B/C/D]: ");
            String players_answer = input.nextLine();
            if (M.get(ans_id).equals(players_answer)) {
                System.out.println("Chinh xac !");
            } else System.out.println("Sai ! " + "\n" + "Answer is: " + M.get(ans_id));

            System.out.println("Bam phim 1 de choi lai, 0 de thoat !");
            if (input.nextInt() == 0) break;
            input.nextLine();
        }
        while (true);

    }

    public void insertFromFile() {
//        File file = new File("D:\\TIN HOC\\javaOOP\\project\\dictionary\\src\\dictionary\\dictionary.txt");
//        System.out.println(file.canRead());
        try {
            File file = new File("src/main/java/Dictionary/dictionary/dictionary.txt");

            Scanner fileInput = new Scanner(file);
            int n = 0;
            String target = "";
            String explain = "";
            while (fileInput.hasNextLine()) {
                String data = fileInput.nextLine();
//                System.out.println(data);
                if (data.isEmpty()) continue;
//                System.out.println(data.charAt(0));
                if ('@' == data.charAt(0)) {
//                    System.out.println(data);
                    if (!target.isEmpty()) {
//                        dictionary.put(target, explain);
//                        words[n++] = new Word(target, explain);
                        addWord(target, explain);
                    }
                    int idx = data.indexOf('/');
                    if (idx == -1) {
                        target = data.substring(1);
                        explain = "";
                    } else {
                        target = data.substring(1, idx - 1);
                        explain = data.substring(idx);
                    }
//                    System.out.println(data);
                } else {
                    explain += "\n" + data;
                }
            }
            fileInput.close();
            System.out.println(target);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println(e);
        }
    }
}
