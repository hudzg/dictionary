package main;

import Dictionary.DictionaryManagement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static DictionaryManagement dictionaryManagement;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane dashboard = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
//        AnchorPane search = FXMLLoader.load(getClass().getResource("SearchScene.fxml"));
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(dashboard.getChildren());
//        root.getChildren().addAll(search.getChildren());
        Scene scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        dictionaryManagement = new DictionaryManagement();
        dictionaryManagement.insertFromFile();
        launch();
    }
}
