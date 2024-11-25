package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MeaningSceneController{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label wordLabel;
    @FXML
    Label meaningLabel;

    public void display(String word, String meaning) {
        wordLabel.setText(word);
        meaningLabel.setText(meaning);
    }
}
