package BrickDestroyer.Controller;

import java.io.IOException;
import java.util.ArrayList;

import BrickDestroyer.Model.GameHighScore;
import BrickDestroyer.View.GameFrame;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller for GameHighScoreFrame
 */
public class GameHighScoreController {
    private GameFrame gameFrame;

    @FXML
    private Button btnBack;

    @FXML
    private Label lblHighScoreTitle;

    @FXML
    private Label lblPlayer;

    @FXML
    private Label lblPlayer_1;

    @FXML
    private Label lblPlayer_2;

    @FXML
    private Label lblPlayer_3;

    @FXML
    private Label lblPlayer_4;

    @FXML
    private Label lblPlayer_5;

    @FXML
    private Label lblPlayer_6;

    @FXML
    private Label lblScore;

    @FXML
    private Label lblScore_1;

    @FXML
    private Label lblScore_2;

    @FXML
    private Label lblScore_3;

    @FXML
    private Label lblScore_4;

    @FXML
    private Label lblScore_5;

    @FXML
    private Label lblScore_6;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTime_1;

    @FXML
    private Label lblTime_2;

    @FXML
    private Label lblTime_3;

    @FXML
    private Label lblTime_4;

    @FXML
    private Label lblTime_5;

    @FXML
    private Label lblTime_6;

    /**
     * to create a new game frame based on the button clicked
     */
    @FXML
    void btnBackClicked() {
        gameFrame.getGameHighScoreFrame().dispose();
        gameFrame.dispose();
        new GameFrame().initialize();
    }

    /**
     * to initialize game high score frame
     * @throws IOException compulsory when involving IO
     */
    @FXML
    public void initialize() throws IOException {
        ArrayList<JSONObject> highScores;
        highScores = GameHighScore.PlayerList();

        btnBack.setText("BACK TO MENU");
        lblHighScoreTitle.setText("HIGH SCORES");
        lblPlayer.setText("NAME");
        lblScore.setText("SCORE");
        lblTime.setText("TIME");
        lblPlayer_1.setText((String) highScores.get(0).get("Name"));
        lblScore_1.setText((String) highScores.get(0).get("Score"));
        lblTime_1.setText((String) highScores.get(0).get("Time"));
        lblPlayer_2.setText((String) highScores.get(1).get("Name"));
        lblScore_2.setText((String) highScores.get(1).get("Score"));
        lblTime_2.setText((String) highScores.get(1).get("Time"));
        lblPlayer_3.setText((String) highScores.get(2).get("Name"));
        lblScore_3.setText((String) highScores.get(2).get("Score"));
        lblTime_3.setText((String) highScores.get(2).get("Time"));
        lblPlayer_4.setText((String) highScores.get(3).get("Name"));
        lblScore_4.setText((String) highScores.get(3).get("Score"));
        lblTime_4.setText((String) highScores.get(3).get("Time"));
        lblPlayer_5.setText((String) highScores.get(4).get("Name"));
        lblScore_5.setText((String) highScores.get(4).get("Score"));
        lblTime_5.setText((String) highScores.get(4).get("Time"));
        lblPlayer_6.setText((String) highScores.get(5).get("Name"));
        lblScore_6.setText((String) highScores.get(5).get("Score"));
        lblTime_6.setText((String) highScores.get(5).get("Time"));
    }

    /**
     * called in GameHighScoreFrame, setter
     * @param gameFrame current game frame
     */
    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }
}

