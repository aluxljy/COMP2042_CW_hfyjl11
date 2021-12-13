package BrickDestroyer.Controller;

import BrickDestroyer.View.GameFrame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * Controller for GameFrame
 */
public class GameFrameController implements WindowFocusListener {
    private GameFrame gameFrame;

    /**
     * called in GameFrame, GameFrameController constructor
     * @param gameFrame current game frame
     */
    public GameFrameController(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    /**
     * to get the state of the window and set the value of a variable based on the state
     * @param windowEvent event for window
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gameFrame.setGaming(true);
    }

    /**
     * to get the state of the window and get the value of a variable based on the state
     * @param windowEvent event for window
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gameFrame.isGaming())
            gameFrame.getGameBoard().onLostFocus();
    }
}
