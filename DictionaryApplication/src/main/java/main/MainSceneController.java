package main;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane menuPane;
    @FXML
    private Button menuButton;
    @FXML
    VBox dashboard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuPane.setVisible(false);
        dashboard.setLayoutX(-200);
    }

    public void setDashboardVisible(){
        menuPane.setVisible(true);
//        dashboard.setLayoutX(0);
        if(dashboard.getLayoutX() >= 0) return;
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(dashboard);
        transition.setByX(200);
        transition.setDuration(Duration.millis(500));
        transition.play();
//        dashboard.setLayoutX(0);
    }

    public void setDashboardHidden(){
        menuPane.setVisible(false);
//        dashboard.setLayoutX(-200);
//        if(dashboard.getLayoutX() <= -200) return;
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(dashboard);
        transition.setByX(-200);
        transition.setDuration(Duration.millis(500));
        transition.play();
        dashboard.setLayoutX(-200);
    }

    public void clickMenuButton(ActionEvent event) throws IOException {
        setDashboardVisible();
    }

    public void clickMenuPaneButton() throws IOException {
        setDashboardHidden();
    }

    public void clickSearchButton(ActionEvent event) throws IOException {
        setDashboardHidden();
        AnchorPane dashboard = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        AnchorPane search = FXMLLoader.load(getClass().getResource("SearchScene.fxml"));
//        AnchorPane root = new AnchorPane();
//        root.getChildren().addAll(search.getChildren());
//        root.getChildren().addAll(dashboard.getChildren());
//        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
//        Scene scene = new Scene(root, 1366, 768);
//        stage.setScene(scene);
//        stage.show();
//        System.out.println(((Node)(event.getSource())).getParent().getParent().getParent().getClass());

//        for(Node node : ((AnchorPane)((Node) (event.getSource())).getScene().getRoot()).getChildren())
//            System.out.println(node.getClass());
//        System.out.println(((AnchorPane)((Node) (event.getSource())).getScene().getRoot()).getChildren().getClass());
//        ((AnchorPane)((Node) (event.getSource())).getScene().getRoot()).getChildren().remove(0);
//        System.out.println(((AnchorPane)((Node) (event.getSource())).getScene().getRoot()).getChildren().size());
//
//        for(Node node : ((AnchorPane)((Node) (event.getSource())).getScene().getRoot()).getChildren())
//            System.out.println(node.getClass());

//        for(int i = 0; i < search.getChildren().size(); i++)
//            ((AnchorPane)(((Node) (event.getSource())).getScene().getRoot())).getChildren().remove(0);

        while (((AnchorPane)((Node) (event.getSource())).getScene().getRoot()).getChildren().size() > dashboard.getChildren().size())
            ((AnchorPane)((Node) (event.getSource())).getScene().getRoot()).getChildren().remove(0);

        for(int i = 0; i < search.getChildren().size(); i++)
            ((AnchorPane)((Node) (event.getSource())).getScene().getRoot()).getChildren().add(0, search.getChildren().get(i));
    }

    public void clickGameButton(ActionEvent event) throws IOException {
        setDashboardHidden();
        AnchorPane dashboard = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        AnchorPane search = FXMLLoader.load(getClass().getResource("GameScene.fxml"));
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(search.getChildren());
        root.getChildren().addAll(dashboard.getChildren());
        dashboard.toFront();
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        Scene scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.show();
    }

    public void clickTranslateButton(ActionEvent event) throws IOException {
        setDashboardHidden();
        AnchorPane dashboard = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        AnchorPane search = FXMLLoader.load(getClass().getResource("TranslateScene.fxml"));
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(search.getChildren());
        root.getChildren().addAll(dashboard.getChildren());
        dashboard.toFront();
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        Scene scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.show();
    }
}
