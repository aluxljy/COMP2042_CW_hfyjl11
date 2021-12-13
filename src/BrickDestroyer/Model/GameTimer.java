package BrickDestroyer.Model;

import java.text.DecimalFormat;
import java.util.TimerTask;
import java.util.Timer;

/**
 * to count down the time when playing
 */
public class GameTimer {
    private int minute;
    private int second;

    private String ddSecond;
    private String ddMinute;
    private Timer timer;
    private TimerTask timerTask;

    private boolean gameStatus;

    private DecimalFormat dFormat;

    /**
     * called in GameBoard, GameTimer constructor
     */
    public GameTimer() {
        second = 0;
        minute = 10;
        gameStatus = false;
        dFormat = new DecimalFormat("00");
        ddSecond = dFormat.format(second);
        ddMinute = dFormat.format(minute);

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (getGameStatus()) {
                    second--;
                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);

                    if (second == -1) {
                        second = 59;
                        minute--;
                        ddSecond = dFormat.format(second);
                        ddMinute = dFormat.format(minute);
                    }
                }
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    /**
     * called in GameBoard, getter
     * @return decimal format seconds
     */
    public String getDdSecond() {
        return ddSecond;
    }

    /**
     * called in GameBoard, getter
     * @return decimal format minutes
     */
    public String getDdMinute() {
        return ddMinute;
    }

    /**
     * called in GameBoardController & GameBoard, setter
     * @param gameStatus current game status
     */
    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }

    /**
     * getter
     * @return current game status
     */
    public boolean getGameStatus(){
        return gameStatus;
    }
}
