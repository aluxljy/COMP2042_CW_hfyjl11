package BrickDestroyer.Controller;

import BrickDestroyer.View.GameFrame;
import BrickDestroyer.View.GameInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for GameInfo
 */
public class GameInfoController implements ActionListener {
    private GameInfo gameInfo;

    /**
     * called in GameInfo, GameInfoController constructor
     * @param gameInfo current gameInfo
     */
    public GameInfoController(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    /**
     * to get the action performed and create a new game frame based on the action performed
     * @param actionEvent event for the action
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == gameInfo.getBackButton()) {
            gameInfo.dispose();
            new GameFrame();
        }
    }
}
