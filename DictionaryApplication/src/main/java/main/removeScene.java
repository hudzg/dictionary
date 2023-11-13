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

public class removeScene implements Initializable {

    @FXML
    public Button addButton;
    @FXML
    public TextField askForSureTextArea;
    @FXML
    public Button continueButton;
    @FXML
    public Button cancelButton;
    @FXML
    public TextArea showMeaningArea;
    @FXML
    public TextArea findWordArea;
    @FXML
    public Label statementText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        findWordArea.setVisible(true);
        askForSureTextArea.setVisible(false);
        continueButton.setVisible(false);
        cancelButton.setVisible(false);
        statementText.setVisible(false);
    }
    public void typingWords(KeyEvent keyEvent) {
        String cur_words = findWordArea.getText();
        //System.out.println(cur_words);
        String meaning_cur_words = Main.dictionaryManagement.dictionaryLookup(cur_words);
        if(meaning_cur_words == null){
            showMeaningArea.setText("*** can not find these words ! ***");
        }
        else{
            showMeaningArea.setText(meaning_cur_words);
        }
        //showMeaningArea.setVisible(true);
    }

    public void clickAddButton(ActionEvent actionEvent) {
        askForSureTextArea.setVisible(true);
        continueButton.setVisible(true);
        cancelButton.setVisible(true);
    }

    public void clickContinueButton(ActionEvent actionEvent) {
        askForSureTextArea.setVisible(false);
        continueButton.setVisible(false);
        cancelButton.setVisible(false);
        statementText.setVisible(true);
        String words = findWordArea.getText();
        Main.dictionaryManagement.removeFromApp(words);

    }

    public void clickCancelButton(ActionEvent actionEvent) {
        askForSureTextArea.setVisible(false);
        continueButton.setVisible(false);
        cancelButton.setVisible(false);

        statementText.setVisible(false);
    }

}
