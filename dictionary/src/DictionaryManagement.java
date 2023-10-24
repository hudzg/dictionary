import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    public DictionaryManagement() {
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
        while (input.nextInt() != 0){

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
        while (input.nextInt() != 0){

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
        while (input.nextInt() != 0){

        }
    }

    public void dictionaryLookup() {
        clearScreen();
        Scanner input = new Scanner(System.in);
        System.out.println("Bạn nhập từ cần tra cứu đê!!!");
        String s = input.nextLine();
        if(!dictionary.containsKey(s)) {
            System.out.println("Không tìm thấy :((");
        }
        else{
            System.out.println("Từ: " + s);
            System.out.println("Nghĩa: " + dictionary.get(s));
        }
        System.out.println("Ấn phím 0 để thoát");
        while (input.nextInt() != 0){

        }

    }

    public void dictionarySearcher() {

    }

    public void insertFromFile() {
//        File file = new File("D:\\TIN HOC\\javaOOP\\project\\dictionary\\src\\dictionary\\dictionary.txt");
//        System.out.println(file.canRead());
        try {
            File file = new File("src/dictionary/dictionary.txt");

            Scanner fileInput = new Scanner(file);
            int n = 1000000;
            String target = "";
            String explain = "";
            while (fileInput.hasNextLine() && n-- > 0) {
                String data = fileInput.nextLine();
//                System.out.println(data);
                if (data.isEmpty()) continue;
//                System.out.println(data.charAt(0));
                if ('@' == data.charAt(0)) {
//                    System.out.println(data);
                    if (!target.isEmpty()) {
                        dictionary.put(target, explain);
                    }
                    int idx = data.indexOf('/');
                    if (idx == -1) {
                        target = data.substring(1);
                        explain = "";
                    }
                    else {
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
        }
    }
}
