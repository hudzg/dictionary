package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class GameSceneController implements Initializable {

    private static final int numRow = 3;
    private static final int numColumn = 4;
    private static final int flagNum = 1000000;
    @FXML
    private GridPane gridPane;
    private int[][] wordItem;
    private GameListener gameListener;
    private int lastIdx;

    private boolean isExist(int idx) {
        for (int i = 0; i < numRow; i++)
            for (int j = 0; j < numColumn; j++)
                if (wordItem[i][j] == idx) return true;
        return false;
    }

    private void handleChosenWord(int idx) {
        System.out.println(lastIdx + " " + idx);
        if(lastIdx == flagNum){
            lastIdx = idx;
            return;
        }
        if(-lastIdx == idx) {
            for(int i = 0; i < numRow; i++)
                for(int j = 0; j < numColumn; j++)
                    if(Math.abs(wordItem[i][j]) == Math.abs(idx)) {
                        gridPane.getChildren().get(i * numColumn + j).setVisible(false);
//                        for (int k = 0; k < gridPane.getChildren().size(); k++)
//                            System.out.println(gridPane.getChildren().get(i).getClass());
                    }
        }
        lastIdx = flagNum;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            int idx = random.nextInt(100);
            while (isExist(idx)) idx = random.nextInt();
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
}
