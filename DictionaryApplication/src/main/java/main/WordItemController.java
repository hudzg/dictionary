package main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class WordItemController {
    @FXML
    private Button button;
    private int idx;
    private GameListener gameListener;
    public void setData(int idx, GameListener gameListener) {
        this.idx = idx;
        this.gameListener = gameListener;
        if(idx > 0) button.setText(Main.dictionaryManagement.getTargetAt(idx));
        else button.setText(Main.dictionaryManagement.getExplainAt(-idx));
        button.addEventHandler(MouseEvent.MOUSE_ENTERED,
//                e -> button.setOpacity(0.8));
                e -> button.setStyle("-fx-background-color: #7f57c2;" +
                        "-fx-background-radius: 16;"));

        button.addEventHandler(MouseEvent.MOUSE_EXITED,
//                e -> button.setOpacity(1));
                e -> button.setStyle("-fx-background-color: #683ab7;" +
                        "-fx-background-radius: 16;"));

        button.focusedProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("text");
            if (newValue) {
                BorderStrokeStyle borderStrokeStyle = new BorderStrokeStyle(
                        StrokeType.CENTERED,
                        StrokeLineJoin.MITER,
                        StrokeLineCap.ROUND,
                        10,
                        0,
                        null
                );
                BorderStroke borderStroke = new BorderStroke(
                        Color.valueOf("#89b73a"),
                        borderStrokeStyle,
                        new CornerRadii(16),
                        new BorderWidths(4));
                button.setBorder(new Border(borderStroke));
//                button.setStyle("-fx-border-color: #673AB7; -fx-border-width: 4px;");
            }
        });

        button.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                button.setBorder(new Border((BorderStroke) null));
//                button.setStyle("-fx-border-color: #000000; -fx-border-width: 1px;");
            }
        });
    }

    public int getIdx() {
        return idx;
    }

    public void click(){
        gameListener.onClickListener(idx);
    }
}
