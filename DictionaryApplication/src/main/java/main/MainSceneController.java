package main;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {

    private static final int duration = 300;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane shadowPane;
    @FXML
    private AnchorPane headerPane;
    @FXML
    private Button searchButton;
    @FXML
    private Button gameButton;
    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button translateButton;
    @FXML
    private Button removeButton;
    @FXML
    private VBox dashboard;
    @FXML
    private ImageView menu;

    private final Image menuImg = new Image(getClass().getResourceAsStream("img/menu.png"));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        shadowPane.setVisible(false);
        dashboard.setLayoutX(-200);
        menu.setImage(menuImg);
        searchButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
//                e -> searchButton.setOpacity(0.8));
                e -> searchButton.setStyle("-fx-background-color: #7f57c2;" +
                        "-fx-background-radius: 16;"));

        searchButton.addEventHandler(MouseEvent.MOUSE_EXITED,
//                e -> searchButton.setOpacity(1));
                e -> searchButton.setStyle("-fx-background-color: #683ab7;" +
                        "-fx-background-radius: 16;"));

        gameButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
//                e -> searchButton.setOpacity(0.8));
                e -> gameButton.setStyle("-fx-background-color: #7f57c2;" +
                        "-fx-background-radius: 16;"));

        gameButton.addEventHandler(MouseEvent.MOUSE_EXITED,
//                e -> searchButton.setOpacity(1));
                e -> gameButton.setStyle("-fx-background-color: #683ab7;" +
                        "-fx-background-radius: 16;"));
        addButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
//                e -> searchButton.setOpacity(0.8));
                e -> addButton.setStyle("-fx-background-color: #7f57c2;" +
                        "-fx-background-radius: 16;"));

        addButton.addEventHandler(MouseEvent.MOUSE_EXITED,
//                e -> searchButton.setOpacity(1));
                e -> addButton.setStyle("-fx-background-color: #683ab7;" +
                        "-fx-background-radius: 16;"));
        translateButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
//                e -> searchButton.setOpacity(0.8));
                e -> translateButton.setStyle("-fx-background-color: #7f57c2;" +
                        "-fx-background-radius: 16;"));

        translateButton.addEventHandler(MouseEvent.MOUSE_EXITED,
//                e -> searchButton.setOpacity(1));
                e -> translateButton.setStyle("-fx-background-color: #683ab7;" +
                        "-fx-background-radius: 16;"));
        removeButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
//                e -> searchButton.setOpacity(0.8));
                e -> removeButton.setStyle("-fx-background-color: #7f57c2;" +
                        "-fx-background-radius: 16;"));

        removeButton.addEventHandler(MouseEvent.MOUSE_EXITED,
//                e -> searchButton.setOpacity(1));
                e -> removeButton.setStyle("-fx-background-color: #683ab7;" +
                        "-fx-background-radius: 16;"));
        modifyButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
//                e -> searchButton.setOpacity(0.8));
                e -> modifyButton.setStyle("-fx-background-color: #7f57c2;" +
                        "-fx-background-radius: 16;"));

        modifyButton.addEventHandler(MouseEvent.MOUSE_EXITED,
//                e -> searchButton.setOpacity(1));
                e -> modifyButton.setStyle("-fx-background-color: #683ab7;" +
                        "-fx-background-radius: 16;"));
    }

    public void setDashboardVisible() {
        shadowPane.setVisible(true);
//        dashboard.setLayoutX(0);
        if (dashboard.getLayoutX() >= 0) return;
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(dashboard);
        transition.setByX(200);
        transition.setDuration(Duration.millis(duration));
        transition.play();
        headerPane.toFront();
        shadowPane.toFront();
        dashboard.toFront();
//        dashboard.setLayoutX(0);
    }

    public void setDashboardHidden() {
        shadowPane.setVisible(false);
//        dashboard.setLayoutX(-200);
//        if(dashboard.getLayoutX() <= -200) return;
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(dashboard);
        transition.setByX(-200);
        transition.setDuration(Duration.millis(duration));
        transition.play();
        dashboard.setLayoutX(-200);
    }

    public void clickMenuButton() throws IOException {
        setDashboardVisible();
    }

    public void clickMenuPaneButton() throws IOException {
        setDashboardHidden();
    }

    public void removeOthers(ActionEvent event) {
        for (int i = 0; i < ((AnchorPane) ((Node) (event.getSource())).getScene().getRoot()).getChildren().size(); i++) {
            if (((AnchorPane) ((Node) (event.getSource())).getScene().getRoot()).getChildren().get(i) != this.dashboard
                    && ((AnchorPane) ((Node) (event.getSource())).getScene().getRoot()).getChildren().get(i) != shadowPane
                    && ((AnchorPane) ((Node) (event.getSource())).getScene().getRoot()).getChildren().get(i) != headerPane) {
                ((AnchorPane) ((Node) (event.getSource())).getScene().getRoot()).getChildren().remove(i);
                i--;
            }
        }
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

//        while (((AnchorPane) ((Node) (event.getSource())).getScene().getRoot()).getChildren().size() > dashboard.getChildren().size())
//            ((AnchorPane) ((Node) (event.getSource())).getScene().getRoot()).getChildren().remove(0);
        removeOthers(event);

        while (!search.getChildren().isEmpty())
            ((AnchorPane) ((Node) (event.getSource())).getScene().getRoot()).getChildren().add(0, search.getChildren().get(0));

//        for(Node node : search.getChildren())
//            System.out.println(node.getClass());
//
//        System.out.println();
//
//        for (Node node : ((AnchorPane) ((Node) (event.getSource())).getScene().getRoot()).getChildren())
//            System.out.println(node.getClass());
    }

    public void clickGameButton(ActionEvent event) throws IOException {
        setDashboardHidden();
        AnchorPane dashboard = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        AnchorPane game = FXMLLoader.load(getClass().getResource("GameScene.fxml"));
//        AnchorPane root = new AnchorPane();
//        root.getChildren().addAll(game.getChildren());
//        root.getChildren().addAll(dashboard.getChildren());
//        dashboard.toFront();
//        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
//        Scene scene = new Scene(root, 1366, 768);
//        stage.setScene(scene);
//        stage.show();

//        while (((AnchorPane) ((Node) (event.getSource())).getScene().getRoot()).getChildren().size() > dashboard.getChildren().size())
//            ((AnchorPane) ((Node) (event.getSource())).getScene().getRoot()).getChildren().remove(0);

        removeOthers(event);

        while (!game.getChildren().isEmpty())
            ((AnchorPane) ((Node) (event.getSource())).getScene().getRoot()).getChildren().add(0, game.getChildren().get(0));
    }

    public void clickTranslateButton(ActionEvent event) throws IOException {
        setDashboardHidden();
        AnchorPane dashboard = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        AnchorPane translate = FXMLLoader.load(getClass().getResource("TranslateScene.fxml"));
//        AnchorPane root = new AnchorPane();
//        root.getChildren().addAll(translate.getChildren());
//        root.getChildren().addAll(dashboard.getChildren());
//        dashboard.toFront();
//        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
//        Scene scene = new Scene(root, 1366, 768);
//        stage.setScene(scene);
//        stage.show();

//        while (((AnchorPane) ((Node) (event.getSource())).getScene().getRoot()).getChildren().size() > dashboard.getChildren().size())
//            ((AnchorPane) ((Node) (event.getSource())).getScene().getRoot()).getChildren().remove(0);

        removeOthers(event);

        while (!translate.getChildren().isEmpty())
            ((AnchorPane) ((Node) (event.getSource())).getScene().getRoot()).getChildren().add(0, translate.getChildren().get(0));
    }

    public void clickAddButton(ActionEvent event) throws IOException {
        setDashboardHidden();
        AnchorPane dashboard = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        AnchorPane translate = FXMLLoader.load(getClass().getResource("AddScene2.fxml"));

        removeOthers(event);

        while (!translate.getChildren().isEmpty())
            ((AnchorPane) ((Node) (event.getSource())).getScene().getRoot()).getChildren().add(0, translate.getChildren().get(0));
    }

    public void clickModifyButton(ActionEvent event) throws IOException {
        setDashboardHidden();
        AnchorPane dashboard = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        AnchorPane translate = FXMLLoader.load(getClass().getResource("ModifyScene.fxml"));

        removeOthers(event);

        while (!translate.getChildren().isEmpty())
            ((AnchorPane) ((Node) (event.getSource())).getScene().getRoot()).getChildren().add(0, translate.getChildren().get(0));
    }
}
