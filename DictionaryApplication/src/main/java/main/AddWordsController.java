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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddWordsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button myButton;
    @FXML
    private Label display_text;
    @FXML
    private TextField FieldMeaning;
    @FXML
    private TextField FieldNewWord;
    @FXML
    private Label titleNewWord;
    @FXML
    private Label titleMeaning;
    @FXML
    private Label StatementText;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        FieldMeaning.setVisible(true);
        FieldNewWord.setVisible(true);
        titleMeaning.setVisible(true);
        titleNewWord.setVisible(true);
        StatementText.setVisible(false);
    }


    public void ClickStartButton(ActionEvent event) throws IOException {



    }


    public void GetMeaning(ActionEvent actionEvent) {
    }

    public void GetNewWord(ActionEvent actionEvent) {
    }

    public void ClickAddButton(ActionEvent actionEvent) {
        String  newWord = FieldNewWord.getText();
        String  newMeaing = FieldMeaning.getText();
        Main.dictionaryManagement.addWord(newWord, newMeaing);
        StatementText.setText("Successfully added !");
        StatementText.setVisible(true);
    }
}
