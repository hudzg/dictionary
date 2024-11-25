package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class SearchController implements Initializable {
    @FXML
    private TextField textField;
    //    @FXML
//    private Button searchButton;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button removeButton;
    @FXML
    private Button speakButton;
    @FXML
    private Button addButton;

    private String selectedString;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label wordLabel;
    @FXML
    private Label meaningLabel;
    @FXML
    private Pane wordPane;
    @FXML
    private ScrollPane meaningPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.setVisible(false);
        wordPane.setVisible(false);
        meaningPane.setVisible(false);
        removeButton.setVisible(false);
        addButton.setVisible(false);
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println("text");
            if (newValue) {
                textField.setStyle("-fx-border-color: #673AB7; -fx-border-width: 2px; " +
                        "-fx-border-radius: 20 20 0 0;" +
                        "-fx-background-radius: 20 20 0 0;");
                listView.setVisible(true);
                if (listView.getItems().isEmpty())
                    listView.getItems().addAll(Main.dictionaryManagement.dictionarySearcher(""));
            }
        });

        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                textField.setStyle("-fx-border-color: #000000; -fx-border-width: 1px;" +
                        "-fx-border-radius: 20;" +
                        "-fx-background-radius: 20;");
                listView.setVisible(false);
            }
        });

        removeButton.setStyle("-fx-background-color: #683ab7;" +
                "-fx-background-radius: 16;");
        addButton.setStyle("-fx-background-color: #683ab7;" +
                "-fx-background-radius: 16;");
        speakButton.setStyle("-fx-background-color: #683ab7;" +
                "-fx-background-radius: 16;");

        removeButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
//                e -> searchButton.setOpacity(0.8));
                e -> removeButton.setStyle("-fx-background-color: #7f57c2;" +
                        "-fx-background-radius: 16;"));

        removeButton.addEventHandler(MouseEvent.MOUSE_EXITED,
//                e -> searchButton.setOpacity(1));
                e -> removeButton.setStyle("-fx-background-color: #683ab7;" +
                        "-fx-background-radius: 16;"));

        addButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
//                e -> searchButton.setOpacity(0.8));
                e -> addButton.setStyle("-fx-background-color: #7f57c2;" +
                        "-fx-background-radius: 16;"));

        addButton.addEventHandler(MouseEvent.MOUSE_EXITED,
//                e -> searchButton.setOpacity(1));
                e -> addButton.setStyle("-fx-background-color: #683ab7;" +
                        "-fx-background-radius: 16;"));

        speakButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
//                e -> searchButton.setOpacity(0.8));
                e -> speakButton.setStyle("-fx-background-color: #7f57c2;" +
                        "-fx-background-radius: 16;"));

        speakButton.addEventHandler(MouseEvent.MOUSE_EXITED,
//                e -> searchButton.setOpacity(1));
                e -> speakButton.setStyle("-fx-background-color: #683ab7;" +
                        "-fx-background-radius: 16;"));

        listView.focusedProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println("list");
            if (!newValue) {
                listView.setVisible(false);
            }
        });
        listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                try {
//
//
                selectedString = listView.getSelectionModel().getSelectedItem();
//                int pos = listView.getSelectionModel().getSelectedIndex();
//                if(pos == -1) return;
//                pos = wordPos.get(pos);
//                selectedString = Main.dictionaryManagement.getTargetAt(pos);
//                System.out.println("Pos: " + pos);
                if (selectedString == null) return;
                display(selectedString, Main.dictionaryManagement.dictionaryLookup(selectedString));
//                    listView.getItems().clear();
                listView.setVisible(false);
//                }
//                catch (Exception e){
//                    System.out.println(e);
//                }


//                FXMLLoader loader = new FXMLLoader(getClass().getResource("MeaningScene.fxml"));
//                try {
//                    root = loader.load();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//                MeaningSceneController scene2Controller = loader.getController();
//                scene2Controller.display(selectedString, Main.dictionaryManagement.dictionaryLookup(selectedString));
//
//                stage = (Stage) listView.getScene().getWindow();
//                scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
            }
        });
    }

    public void search() {
        String text = textField.getText();
        listView.getItems().clear();
//        wordPos = Main.dictionaryManagement.getWord(text);
//        for(int x : wordPos) listView.getItems().add(Main.dictionaryManagement.getTargetAt(x));
        listView.getItems().addAll(Main.dictionaryManagement.dictionarySearcher(text));
        listView.setVisible(true);
        listView.toFront();
    }

    public void display(String word, String meaning) {
        wordPane.setVisible(true);
        meaningPane.setVisible(true);
        removeButton.setVisible(true);
        if (Main.dictionaryManagement == Main.englishDict)
            addButton.setVisible(true);
        wordLabel.setText(word);
        meaningLabel.setText(meaning);
        wordLabel.toFront();
        meaningLabel.toFront();
        textField.setText(word);
    }

    public void clickRemoveButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Do you want to remove \"" + selectedString + "\" ?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println(selectedString);
            Main.dictionaryManagement.removeFromApp(selectedString);
            wordPane.setVisible(false);
            meaningPane.setVisible(false);
            removeButton.setVisible(false);
            listView.setVisible(false);
            addButton.setVisible(false);
            textField.setText("");
            selectedString = "";
            listView.getItems().clear();
            textField.getParent().requestFocus();
        }
    }

    public void clickAddButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Do you want to add \"" + selectedString + "\" to your dictionary ?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println(selectedString);
            Main.myDict.addWord(selectedString, Main.dictionaryManagement.dictionaryLookup(selectedString));
        }
    }

    public void speak() {
        if (selectedString == null) return;
//        try {
//            // Set property as Kevin Dictionary
//            System.setProperty(
//                    "freetts.voices",
//                    "com.sun.speech.freetts.en.us"
//                            + ".cmu_us_kal.KevinVoiceDirectory");
//
//            // Register Engine
//            Central.registerEngineCentral(
//                    "com.sun.speech.freetts"
//                            + ".jsapi.FreeTTSEngineCentral");
//
//            // Create a Synthesizer
//            Synthesizer synthesizer
//                    = Central.createSynthesizer(
//                    new SynthesizerModeDesc(Locale.US));
//
//            // Allocate synthesizer
//            if (synthesizer != null) {
//                synthesizer.allocate();
//
//                // Resume Synthesizer
//                synthesizer.resume();
//
//                // Speaks the given text
//                // until the queue is empty.
//                synthesizer.speakPlainText(
//                        selectedString, null);
//                synthesizer.waitEngineState(
//                        Synthesizer.QUEUE_EMPTY);
//
//                // Deallocate the Synthesizer.
//                synthesizer.deallocate();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice;

        voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {

            voice.allocate();
        }
        try {

//            voice.setRate(190);

//            voice.setPitch(150);

//            voice.setVolume(20);

            voice.speak(selectedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
