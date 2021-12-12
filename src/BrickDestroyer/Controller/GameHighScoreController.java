package BrickDestroyer.Controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import BrickDestroyer.Model.GameHighScore;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameHighScoreController {

    @FXML
    private Button btnBack;

    @FXML
    private Label lblHighScoreTitle;

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
    public void initialize() throws IOException, ParseException{

        ArrayList<JSONObject> highScores = new ArrayList<>();

        highScores = GameHighScore.PlayerList();

        lblPlayer_1.setText((String) highScores.get(0).get("Name"));
        lblScore_1.setText((String) highScores.get(0).get("Score"));
        lblPlayer_2.setText((String) highScores.get(1).get("Name"));
        lblScore_2.setText((String) highScores.get(1).get("Score"));
        lblPlayer_3.setText((String) highScores.get(2).get("Name"));
        lblScore_3.setText((String) highScores.get(2).get("Score"));
        lblPlayer_4.setText((String) highScores.get(3).get("Name"));
        lblScore_4.setText((String) highScores.get(3).get("Score"));
        lblPlayer_5.setText((String) highScores.get(4).get("Name"));
        lblScore_5.setText((String) highScores.get(4).get("Score"));
        lblPlayer_6.setText((String) highScores.get(5).get("Name"));
        lblScore_6.setText((String) highScores.get(5).get("Score"));
    }
}

