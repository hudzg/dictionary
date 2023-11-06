package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranslateSceneController {
    @FXML
    private TextField text;
    @FXML
    private Label translatedText;

    String sourceLang = "en";
    String targetLang = "vi";

    private static final Pattern UNICODE_PATTERN = Pattern.compile("\\\\u([0-9A-Fa-f]{4})");

    public static String normalizeUnicode(String input) {
        Matcher matcher = UNICODE_PATTERN.matcher(input);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            char unicodeChar = (char) Integer.parseInt(matcher.group(1), 16);
            matcher.appendReplacement(sb, Matcher.quoteReplacement(Character.toString(unicodeChar)));
        }
        matcher.appendTail(sb);
        byte[] utf8Bytes = sb.toString().getBytes(StandardCharsets.UTF_8);
        return new String(utf8Bytes, StandardCharsets.UTF_8);
    }

    public static String normalizeSpace(String input) {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '\\'){
                res.append('\n');
                i++;
            }
            else{
                res.append(input.charAt(i));
            }
        }
        return String.valueOf(res);
    }

    public void translate(){
        String textToTranslate = text.getText();

        String apiKey = "df4dd4c25ce831047022";

        String urlStr = "https://api.mymemory.translated.net/get?q=" + URLEncoder.encode(textToTranslate, StandardCharsets.UTF_8) + "&langpair=" + sourceLang + "|" + targetLang + "&key=" + apiKey;

        // Send GET request to MyMemory API
        URL link;
        try {
            link = new URL(urlStr);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) link.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }

        // Read response
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String inputLine;
        StringBuilder response = new StringBuilder();
        while (true) {
            try {
                if (!((inputLine = in.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            response.append(inputLine);
        }
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Extract translation from JSON response
        String translation = response.toString().split("\"translatedText\":\"")[1].split("\"")[0];
        // Print translation
        String res1 = normalizeUnicode(translation);
        String ans = normalizeSpace(res1);
//        System.out.println(ans);
        if(ans.equals("QUERY LENGTH LIMIT EXCEEDED. MAX ALLOWED QUERY : 500 CHARS")) {
            translatedText.setText("Sorry, I can translate up to a maximum of 500 characters per request");
        }
        else translatedText.setText(ans);
    }
}
