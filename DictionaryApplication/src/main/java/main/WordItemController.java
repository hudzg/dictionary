package main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WordItemController {
    @FXML
    private Button button;
    private int idx;
    private GameListener gameListener;
    public void setData(int idx, GameListener gameListener) {
        this.idx = idx;
        this.gameListener = gameListener;
        button.setText(idx + "");
    }

    public int getIdx() {
        return idx;
    }

    public void click(){
        gameListener.onClickListener(idx);
    }
}
