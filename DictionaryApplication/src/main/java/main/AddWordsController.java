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
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddWordsController implements Initializable {
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





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        addButton.setVisible(true);
        FieldMeaning.setVisible(true);
        FieldNewWord.setVisible(true);
        titleMeaning.setVisible(true);
        titleNewWord.setVisible(true);
    }

    public void ClickAddButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("are you sure you want to add ?");
        //  alert.setContentText("Do you want to save before exiting?: ");

        if(alert.showAndWait().get() == ButtonType.OK){
            String word = FieldNewWord.getText();
            String meaning = FieldMeaning.getText();

            /* complete this ........................
            * call addword function to add
            * */
              Main.dictionaryManagement.addWord(word, meaning);
        }
    }


}
