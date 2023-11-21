package main;

import Dictionary.DictionaryManagement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {


    public static DictionaryManagement dictionaryManagement;
    public static DictionaryManagement myDict;
    public static DictionaryManagement englishDict;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane dashboard = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
//        AnchorPane search = FXMLLoader.load(getClass().getResource("SearchScene.fxml"));
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(dashboard.getChildren());

        root.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            Node node = (Node) event.getTarget();
//            System.out.println(node.getClass());
            node.requestFocus();
        });

//        root.getChildren().addAll(search.getChildren());
        Scene scene = new Scene(root, 1366, 768);
       // scene.getStylesheets().add(getClass().getResource("Modifycss.css").toExternalForm()).
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        dictionaryManagement = new DictionaryManagement();
        dictionaryManagement.insertFromFile();
        myDict = new DictionaryManagement();
        myDict.insertFromFile("src/main/java/Dictionary/dictionary/myDict.txt");
        englishDict = dictionaryManagement;
        launch();
    }
}
