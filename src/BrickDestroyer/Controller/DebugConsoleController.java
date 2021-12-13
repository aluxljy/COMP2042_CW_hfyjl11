package BrickDestroyer.Controller;

import BrickDestroyer.Model.Ball;
import BrickDestroyer.View.DebugConsole;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Controller for DebugConsole
 */
public class DebugConsoleController implements WindowListener {
    private DebugConsole debugConsole;

    /**
     * called in DebugConsole, DebugConsoleController constructor
     * @param debugConsole current debug console
     */
    public DebugConsoleController(DebugConsole debugConsole) {
        this.debugConsole = debugConsole;
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    /**
     * to get the state of the window and perform actions based on the state
     * @param windowEvent event of window
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        debugConsole.getGameBoard().repaint();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    /**
     * to get the state of the window and set the value of a variable based on the state
     * @param windowEvent event of window
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball ball = debugConsole.getWall().getBall();
        debugConsole.getDebugPanel().setValues(ball.getSpeedX(),ball.getSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }

    /**
     * to set the location of the debug console
     */
    private void setLocation() {
        int x = ((debugConsole.getOwner().getWidth() - debugConsole.getWidth()) / 2) + debugConsole.getOwner().getX();
        int y = ((debugConsole.getOwner().getHeight() - debugConsole.getHeight()) / 2) + debugConsole.getOwner().getY();
        debugConsole.setLocation(x,y);
    }
}
