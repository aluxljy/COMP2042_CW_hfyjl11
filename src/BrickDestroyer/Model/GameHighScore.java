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

/**
 * to handle the high scores of the game
 */
public class GameHighScore {
    /**
     * to read the data in the json file to an array list
     * @return ranking array list
     * @throws IOException compulsory when involving IO
     */
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

    /**
     * to check if the score made it into the top 6 of the high score list
     * @param score score obtained by the player
     * @param time time remaining for the player to complete all levels
     * @return true or false
     * @throws IOException compulsory when involving IO
     */
    public static boolean Check(int score, String time) throws IOException {
        ArrayList<JSONObject> playerList = PlayerList();
        String[] timeString = time.split(":");

        int seconds = Integer.parseInt(timeString[0]) * 60 + Integer.parseInt(timeString[1]);

        for(Object player: playerList){
            JSONObject jsonPlayer = (JSONObject) player;
            String[] previousTimeString = ((String)jsonPlayer.get("Time")).split(":");
            int previousSeconds = Integer.parseInt(previousTimeString[0]) * 60 + Integer.parseInt((previousTimeString[1]));
            if(score > Integer.parseInt((String) jsonPlayer.get("Score"))) {
                return true;
            }
            else if(score == Integer.parseInt((String) jsonPlayer.get("Score")) && seconds > previousSeconds) {
                return true;
            }
        }
        return false;
    }

    /**
     * to add a new player that made into the top 6 into the high score list
     * @param playerName name of the player
     * @param highScore high score obtained by the player
     * @param time time remaining for the player to complete all levels
     * @throws IOException compulsory when involving IO
     */
    public static void AddPlayer(String playerName, Integer highScore, String time) throws IOException {
        JSONObject newPlayer = new JSONObject();
        newPlayer.put("Name",playerName);
        newPlayer.put("Score",highScore.toString());
        newPlayer.put("Time",time);

        JSONArray newHighScores = new JSONArray();

        ArrayList<JSONObject> playerList = PlayerList();

        boolean added = false;

        String[] timeString = time.split(":");

        int seconds = Integer.parseInt(timeString[0]) * 60 + Integer.parseInt(timeString[1]);
        int n = 0;

        for (int i = 0; i < 6; i++) {
            String[] previousTimeString = ((String)playerList.get(i).get("Time")).split(":");
            int previousSeconds = Integer.parseInt(previousTimeString[0]) * 60 + Integer.parseInt((previousTimeString[1]));

            if (!added && (Integer.parseInt((String) newPlayer.get("Score")) > Integer.parseInt((String) playerList.get(i).get("Score")))) {
                newHighScores.put(i, newPlayer);
                added = true;
                i++;

                if (i >= 6 ) {
                    break;
                }
                newHighScores.put(i, playerList.get(n));
                n++;
            }
            else if(!added && (Integer.parseInt((String) newPlayer.get("Score")) == Integer.parseInt((String) playerList.get(i).get("Score"))) && seconds > previousSeconds) {
                newHighScores.put(i, newPlayer);
                added = true;
                i++;

                if (i >= 6 ) {
                    break;
                }
                newHighScores.put(i, playerList.get(n));
                n++;
            }
            else {
                newHighScores.put(i, playerList.get(n));
                n++;
            }
        }

        try (FileWriter file = new FileWriter("src/BrickDestroyer/Model/Highscores.json")) {
            file.write(newHighScores.toString());
            file.flush();
        }
    }
}
