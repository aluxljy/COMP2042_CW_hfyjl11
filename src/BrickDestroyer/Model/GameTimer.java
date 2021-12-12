package BrickDestroyer.Model;

import java.text.DecimalFormat;
import java.util.TimerTask;
import java.util.Timer;

public class GameTimer {
    private int minute;
    private int second;

    private String ddSecond;
    private String ddMinute;
    private Timer timer;
    private TimerTask timerTask;

    private boolean gameStatus;

    private DecimalFormat dFormat;

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

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public String getDdSecond() {
        return ddSecond;
    }

    public String getDdMinute() {
        return ddMinute;
    }

    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }

    public boolean getGameStatus(){
        return gameStatus;
    }

    public void resetTimer(){
        second = 0;
        minute = 10;
    }
}
