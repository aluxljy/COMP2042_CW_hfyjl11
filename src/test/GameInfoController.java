package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameInfoController implements ActionListener {
    private GameInfo gameInfo;

    /**
     * called in GameInfo
     */
    // GameInfoController constructor
    public GameInfoController(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == gameInfo.getBackButton()) {
            gameInfo.dispose();
            new GameFrame();
        }
    }
}
