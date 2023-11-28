package main;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class GameSceneController implements Initializable {

    private static final int numRow = 3;
    private static final int numColumn = 4;
    private static final int flagNum = 1000000;
    private static final int INIT_HP = 3;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button playButton;
    @FXML
    private Label timeLabel;
    @FXML
    private HBox HPBar;
    private int[][] wordItem;
    private GameListener gameListener;
    private int lastIdx;
    private int HP;
    private final Image HPImg = new Image(getClass().getResourceAsStream("img/heart.png"));
    AnimationTimer timer = new AnimationTimer() {
        private long timestamp = 0;
        private long time = 0;
        private long fraction = 0;

        @Override
        public void start() {
            // current time adjusted by remaining time from last run
            timestamp = System.currentTimeMillis() - fraction;
            time = 0;
            timeLabel.setText("Time: " + time);
            super.start();
        }

        @Override
        public void stop() {
            super.stop();
            // save leftover time not handled with the last update
            fraction = System.currentTimeMillis() - timestamp;
        }

        @Override
        public void handle(long now) {
            long newTime = System.currentTimeMillis();
            if (timestamp + 1000 <= newTime) {
                long deltaT = (newTime - timestamp) / 1000;
                time += deltaT;
                timestamp += 1000 * deltaT;
                timeLabel.setText("Time: " + time);
            }
        }
    };

    private boolean isExist(int idx) {
        for (int i = 0; i < numRow; i++)
            for (int j = 0; j < numColumn; j++)
                if (wordItem[i][j] == idx) return true;
        return false;
    }

    private void handleChosenWord(int idx) {
        System.out.println(lastIdx + " " + idx);
        if (lastIdx == flagNum) {
            lastIdx = idx;
            return;
        }
        if (-lastIdx == idx) {
            for (int i = 0; i < numRow; i++)
                for (int j = 0; j < numColumn; j++)
                    if (Math.abs(wordItem[i][j]) == Math.abs(idx)) {
                        gridPane.getChildren().get(i * numColumn + j).setVisible(false);
//                        for (int k = 0; k < gridPane.getChildren().size(); k++)
//                            System.out.println(gridPane.getChildren().get(i).getClass());
                    }
        } else {
            decreaseHP();
            for (int i = 0; i < numRow; i++)
                for (int j = 0; j < numColumn; j++)
                    if (wordItem[i][j] == lastIdx || wordItem[i][j] == idx) {
                        Node node = gridPane.getChildren().get(i * numColumn + j);
                        AnchorPane anchorPane = (AnchorPane) (node);
                        Button button = (Button) anchorPane.getChildren().get(0);
                        button.setBorder(new Border((BorderStroke) null));
                    }
        }
        gridPane.requestFocus();
        lastIdx = flagNum;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playButton.setStyle("-fx-background-color: #683ab7;" +
                "-fx-background-radius: 16;");
        playButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
//                e -> searchButton.setOpacity(0.8));
                e -> playButton.setStyle("-fx-background-color: #7f57c2;" +
                        "-fx-background-radius: 16;"));

        playButton.addEventHandler(MouseEvent.MOUSE_EXITED,
//                e -> searchButton.setOpacity(1));
                e -> playButton.setStyle("-fx-background-color: #683ab7;" +
                        "-fx-background-radius: 16;"));
        gridPane.setVisible(false);
        lastIdx = flagNum;
        wordItem = new int[numRow][numColumn];
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numColumn; j++) {
                wordItem[i][j] = flagNum;
            }
        }
        gameListener = new GameListener() {
            @Override
            public void onClickListener(int idx) {
                handleChosenWord(idx);
            }
        };
        Random random = new Random();
        for (int i = 0; i < numRow * numColumn / 2; i++) {
            int idx = random.nextInt(1000) + 1;
            while (isExist(idx)) idx = random.nextInt(1000) + 1;
            int x = random.nextInt(numRow), y = random.nextInt(numColumn);
            while (wordItem[x][y] != flagNum) {
                x = random.nextInt(numRow);
                y = random.nextInt(numColumn);
            }
            wordItem[x][y] = idx;
            while (wordItem[x][y] != flagNum) {
                x = random.nextInt(numRow);
                y = random.nextInt(numColumn);
            }
            wordItem[x][y] = -idx;
        }

        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numColumn; j++) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("WordItem.fxml"));
                    AnchorPane item = fxmlLoader.load();
                    WordItemController wordItemController = fxmlLoader.getController();

                    wordItemController.setData(wordItem[i][j], gameListener);

                    gridPane.add(item, j, i);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void setHP(int HP) {
        this.HP = HP;
        HPBar.getChildren().clear();
        for (int i = 0; i < HP; i++) {
            ImageView imageView = new ImageView(HPImg);
            imageView.setFitWidth(32);
            imageView.setFitHeight(32);
            HPBar.getChildren().add(imageView);
        }
    }

    private void decreaseHP() {
        setHP(Math.max(0, HP - 1));
    }

    public void playGame() {
        setHP(INIT_HP);
        timer.start();
//        timer.stop();
        gridPane.setVisible(true);
        playButton.setVisible(false);
        gridPane.getParent().requestFocus();
        gridPane.getChildren().clear();
        lastIdx = flagNum;
        wordItem = new int[numRow][numColumn];
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numColumn; j++) {
                wordItem[i][j] = flagNum;
            }
        }
        gameListener = new GameListener() {
            @Override
            public void onClickListener(int idx) {
                handleChosenWord(idx);
            }
        };
        Random random = new Random();
        for (int i = 0; i < numRow * numColumn / 2; i++) {
            int idx = random.nextInt(1000) + 1;
            while (isExist(idx)) idx = random.nextInt(1000) + 1;
            int x = random.nextInt(numRow), y = random.nextInt(numColumn);
            while (wordItem[x][y] != flagNum) {
                x = random.nextInt(numRow);
                y = random.nextInt(numColumn);
            }
            wordItem[x][y] = idx;
            while (wordItem[x][y] != flagNum) {
                x = random.nextInt(numRow);
                y = random.nextInt(numColumn);
            }
            wordItem[x][y] = -idx;
        }

        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numColumn; j++) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("WordItem.fxml"));
                    AnchorPane item = fxmlLoader.load();
                    WordItemController wordItemController = fxmlLoader.getController();

                    wordItemController.setData(wordItem[i][j], gameListener);

                    gridPane.add(item, j, i);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void clickPlayButton(ActionEvent event) {
//        playButton.setVisible(false);
        playGame();
    }
}
