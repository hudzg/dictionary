package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyController implements Initializable {
    @FXML
    private Label wordTitle;
    @FXML
    private Label newMeaningTitle;
    @FXML
    private TextArea newMeaningArea;
    @FXML
    private TextArea wordArea;
    @FXML
    private TextArea warningTextArea;
    @FXML
    private Button okButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        wordTitle.setVisible(true);
        newMeaningTitle.setVisible(true);
        newMeaningArea.setVisible(true);
        wordArea.setVisible(true);
        warningTextArea.setVisible(false);
    }
    public void typingWords(KeyEvent event){
        String cur_words = wordArea.getText();
        String meaning_cur_words = Main.dictionaryManagement.dictionaryLookup(cur_words);
        if(meaning_cur_words == null){
            warningTextArea.setText("*** can not find these words ! ***");
        }
        else{
            warningTextArea.setText(meaning_cur_words);
        }
        warningTextArea.setVisible(true);
    }
    public void ClickOkButton(ActionEvent actionEvent) {
        String cur_words = wordArea.getText();
        String new_meaning = newMeaningArea.getText();
        Main.dictionaryManagement.changeMeaning(cur_words, new_meaning);
        warningTextArea.setText("Okay");
        warningTextArea.setVisible(true);
    }


}
