package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyController implements Initializable {
    @FXML
    private Label wordTitle;
    @FXML
    private Label newMeaningTitle;

    @FXML
    private TextArea wordArea;
    @FXML
    private TextArea changeMeaningArea;

    @FXML
    private Button okButton;

    @FXML
    public ListView<String> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        wordTitle.setVisible(true);
        newMeaningTitle.setVisible(true);

        wordArea.setVisible(true);
        changeMeaningArea.setVisible(true);
        listView.setVisible(false);
    }


    public void typingWords(KeyEvent event){
        String text = wordArea.getText();
        listView.getItems().clear();
        listView.getItems().addAll(Main.dictionaryManagement.dictionarySearcher(text));
        listView.setVisible(true);
        listView.toFront();

    }

    public void ClickOkButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Are you sure you want to change ?");
      //  alert.setContentText("Do you want to save before exiting?: ");

        if(alert.showAndWait().get() == ButtonType.OK){
            String word = wordArea.getText();
            String newMeaning = changeMeaningArea.getText();
            Main.dictionaryManagement.changeMeaning(word, newMeaning);
        }
    }

    public void selectWord(MouseEvent mouseEvent) {
        String selectedString = listView.getSelectionModel().getSelectedItem();
        wordArea.setText(selectedString);
        listView.setVisible(false);
        selectedString = Main.dictionaryManagement.dictionaryLookup(selectedString);

        changeMeaningArea.setText(selectedString);

    }

}
