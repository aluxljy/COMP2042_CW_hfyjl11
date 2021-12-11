package test;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DebugConsoleController implements WindowListener {
    private DebugConsole debugConsole;

    /**
     * called in DebugConsole
     */
    // DebugConsoleController constructor
    public DebugConsoleController(DebugConsole debugConsole) {
        this.debugConsole = debugConsole;
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

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

    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball ball = debugConsole.getWall().getBall();
        debugConsole.getDebugPanel().setValues(ball.getSpeedX(),ball.getSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }

    private void setLocation() {
        int x = ((debugConsole.getOwner().getWidth() - debugConsole.getWidth()) / 2) + debugConsole.getOwner().getX();
        int y = ((debugConsole.getOwner().getHeight() - debugConsole.getHeight()) / 2) + debugConsole.getOwner().getY();
        debugConsole.setLocation(x,y);
    }
}
