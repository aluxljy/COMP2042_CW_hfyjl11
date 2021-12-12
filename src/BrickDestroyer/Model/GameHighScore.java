package BrickDestroyer.Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GameHighScore {
    public static ArrayList<JSONObject> PlayerList() throws IOException {
        FileReader file = new FileReader("src/BrickDestroyer/Model/Highscores.json");
        JSONTokener token = new JSONTokener(file);
        JSONArray list = new JSONArray (token);

        ArrayList<JSONObject> ranking = new ArrayList<>();   

        for (Object player: list) {
            JSONObject jsonPlayer = (JSONObject) player;
            ranking.add((JSONObject) jsonPlayer);
        }

        Collections.sort(ranking, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject left, JSONObject right) {
                String leftScore = (String) left.get("Score");
                String rightScore = (String) right.get("Score");

                return Integer.compare(Integer.parseInt(rightScore), Integer.parseInt(leftScore)); // parse string id to integer then compare
            }
        });
        return ranking;
    }

    // to check if the score is a top 6 high score
    public static boolean Check(int score) throws IOException {
        ArrayList<JSONObject> playerList = PlayerList();

        for(Object player: playerList){
            JSONObject jsonPlayer = (JSONObject) player;
            if (score > Integer.parseInt((String) jsonPlayer.get("Score"))) {
                return true;
            }
        }
        return false;
    }

    public static void AddPlayer(String playerName, Integer highScore) throws IOException {
        JSONObject newPlayer = new JSONObject();
        newPlayer.put("Name",playerName);
        newPlayer.put("Score",highScore.toString());

        JSONArray newHighScores = new JSONArray();

        ArrayList<JSONObject> playerList = PlayerList();

        boolean added = false;

        for (int i = 0; i < 6; i++) {
            if (!added && (Integer.parseInt((String) newPlayer.get("Score")) > Integer.parseInt((String) playerList.get(i).get("Score")))) {
                newHighScores.put(i, newPlayer);
                added = true;
                i++;

                if (i >= 6 ) {
                    break;
                }
            }
            newHighScores.put(i, playerList.get(i));
        }

        try (FileWriter file = new FileWriter("src/BrickDestroyer/Model/Highscores.json")) {
            file.write(newHighScores.toString());
            file.flush();
        }
    }
}
