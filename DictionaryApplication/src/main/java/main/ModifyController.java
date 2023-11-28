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
    private TextField wordArea;
    @FXML
    private TextArea changeMeaningArea;

    @FXML
    private Button modifyButton;

    @FXML
    public ListView<String> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        wordTitle.setVisible(true);
        newMeaningTitle.setVisible(true);

        wordArea.setVisible(true);
        changeMeaningArea.setVisible(true);
        listView.setVisible(false);

        changeMeaningArea.focusedProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println("text");
            if (newValue) {
                changeMeaningArea.setStyle("-fx-border-color: #673AB7; -fx-border-width: 2px; " +
                        "-fx-border-radius: 20;" +
                        "-fx-background-radius: 20;" +
                        "-fx-background-color: #FFFFFF;");
            }
        });

        changeMeaningArea.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                changeMeaningArea.setStyle("-fx-border-color: #000000; -fx-border-width: 1px;" +
                        "-fx-border-radius: 20;" +
                        "-fx-background-radius: 20;" +
                        "-fx-background-color: #FFFFFF;");
            }
        });

        wordArea.focusedProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println("text");
            if (newValue) {
                wordArea.setStyle("-fx-border-color: #673AB7; -fx-border-width: 2px; " +
                        "-fx-border-radius: 20 20 0 0;" +
                        "-fx-background-radius: 20 20 0 0;");
                listView.setVisible(true);
            }
        });

        wordArea.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                wordArea.setStyle("-fx-border-color: #000000; -fx-border-width: 1px;" +
                        "-fx-border-radius: 20;" +
                        "-fx-background-radius: 20;");
                listView.setVisible(false);
            }
        });

        modifyButton.setStyle("-fx-background-color: #683ab7;" +
                "-fx-background-radius: 16;");
        modifyButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
//                e -> searchButton.setOpacity(0.8));
                e -> modifyButton.setStyle("-fx-background-color: #7f57c2;" +
                        "-fx-background-radius: 16;"));

        modifyButton.addEventHandler(MouseEvent.MOUSE_EXITED,
//                e -> searchButton.setOpacity(1));
                e -> modifyButton.setStyle("-fx-background-color: #683ab7;" +
                        "-fx-background-radius: 16;"));
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
