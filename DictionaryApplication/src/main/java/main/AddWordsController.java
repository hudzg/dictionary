package main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddWordsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button addButton;
    @FXML
    private Label display_text;
    @FXML
    private TextArea FieldMeaning;
    @FXML
    private TextArea FieldNewWord;
    @FXML
    private Label titleNewWord;
    @FXML
    private Label titleMeaning;
    @FXML
    private Label StatementText;


    @FXML
    private TextField askForSureTextArea;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        askForSureTextArea.setVisible(false);
        okButton.setVisible(false);
        cancelButton.setVisible(false);

        addButton.setVisible(true);
        FieldMeaning.setVisible(true);
        FieldNewWord.setVisible(true);
        titleMeaning.setVisible(true);
        titleNewWord.setVisible(true);
        StatementText.setVisible(false);


    }

    public void ClickAddButton(ActionEvent actionEvent) {
        askForSureTextArea.toFront();
        askForSureTextArea.setVisible(true);
        okButton.toFront();
        okButton.setVisible(true);
        cancelButton.setVisible(true);
        cancelButton.toFront();
        StatementText.setVisible(false);
    }

    public void clickOkButton(ActionEvent actionEvent) {

        String  newWord = FieldNewWord.getText();
        String  newMeaing = FieldMeaning.getText();
        if(!newWord.equals("") && !newMeaing.equals(""))  Main.dictionaryManagement.addWord(newWord, newMeaing);



        addButton.setVisible(true);
        askForSureTextArea.setVisible(false);
        okButton.setVisible(false);
        cancelButton.setVisible(false);
        StatementText.setText("Great! The addition was successful !");
        StatementText.setVisible(true);
    }

    public void clickCancelButton(ActionEvent actionEvent) {
        askForSureTextArea.setVisible(false);
        okButton.setVisible(false);
        cancelButton.setVisible(false);
        StatementText.setVisible(false);
    }
}
