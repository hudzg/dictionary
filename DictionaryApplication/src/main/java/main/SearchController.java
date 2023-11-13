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
    @FXML
    private Button searchButton;
    @FXML
    private ListView<String> listView;

    private String selectedString;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private List<Integer> wordPos;

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
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println("text");
            if (newValue) {
                textField.setStyle("-fx-border-color: #673AB7; -fx-border-width: 4px;");
                listView.setVisible(true);
            }
        });

        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                textField.setStyle("-fx-border-color: #000000; -fx-border-width: 1px;");
                listView.setVisible(false);
            }
        });

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
//                selectedString = listView.getSelectionModel().getSelectedItem();
                int pos = listView.getSelectionModel().getSelectedIndex();
                if(pos == -1) return;
                pos = wordPos.get(pos);
                selectedString = Main.dictionaryManagement.getTargetAt(pos);
                System.out.println("Pos: " + pos);
//                if (selectedString == null) return;
                display(selectedString, Main.dictionaryManagement.getExplainAt(pos));
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
        wordPos = Main.dictionaryManagement.getWord(text);
        for(int x : wordPos) listView.getItems().add(Main.dictionaryManagement.getTargetAt(x));
        listView.setVisible(true);
        listView.toFront();
    }

    public void display(String word, String meaning) {
        wordPane.setVisible(true);
        meaningPane.setVisible(true);
        wordLabel.setText(word);
        meaningLabel.setText(meaning);
        wordLabel.toFront();
        meaningLabel.toFront();
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
