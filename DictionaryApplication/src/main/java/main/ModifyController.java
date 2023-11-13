package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

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
    private TextField askForSureTextArea;
    @FXML
    private Button okButton;
    @FXML
    private Button continueButton;
    @FXML
    private Button cancelButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        wordTitle.setVisible(true);
        newMeaningTitle.setVisible(true);
        newMeaningArea.setVisible(true);
        wordArea.setVisible(true);
        warningTextArea.setVisible(false);

        askForSureTextArea.setVisible(false);
        continueButton.setVisible(false);
        cancelButton.setVisible(false);
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
        if(meaning_cur_words == null){
            newMeaningArea.setText("*** can not find these words ! ***");
        }
        else{
            newMeaningArea.setText(meaning_cur_words);
        }
        newMeaningArea.setVisible(true);
    }
    public void ClickOkButton(ActionEvent actionEvent) {
        askForSureTextArea.setVisible(true);
        continueButton.setVisible(true);
        cancelButton.setVisible(true);
        warningTextArea.setVisible(false);
    }


    public void clickCancelButton(ActionEvent actionEvent) {
        askForSureTextArea.setVisible(false);
        continueButton.setVisible(false);
        cancelButton.setVisible(false);
    }

    public void clickContinueButton(ActionEvent actionEvent) {
        String cur_words = wordArea.getText();
        String new_meaning = newMeaningArea.getText();
        Main.dictionaryManagement.changeMeaning(cur_words, new_meaning);
        askForSureTextArea.setVisible(false);
        continueButton.setVisible(false);
        cancelButton.setVisible(false);
    }
}
