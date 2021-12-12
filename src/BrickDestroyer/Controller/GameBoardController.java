package BrickDestroyer.Controller;

import BrickDestroyer.View.GameBoard;
import BrickDestroyer.View.GameFrame;

import java.awt.*;
import java.awt.event.*;

public class GameBoardController implements KeyListener, MouseListener, MouseMotionListener {
    private GameBoard gameBoard;

    /**
     * called in GameBoard
     */
    // GameBoardController constructor
    public GameBoardController(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_A) {
            gameBoard.getWall().getPlayer().moveLeft();
        }
        else if(keyEvent.getKeyCode() == KeyEvent.VK_D) {
            gameBoard.getWall().getPlayer().moveRight();
        }
        else if(keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gameBoard.setShowPauseMenu(!gameBoard.isShowPauseMenu());
            gameBoard.repaint();
            gameBoard.getGameTimer().stop();
        }
        else if(keyEvent.getKeyCode() == KeyEvent.VK_SPACE) {
            if(!gameBoard.isShowPauseMenu())
                if(gameBoard.getGameTimer().isRunning())
                    gameBoard.getGameTimer().stop();
                else
                    gameBoard.getGameTimer().start();
        }
        else if(keyEvent.getKeyCode() == KeyEvent.VK_F1) {
            if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                gameBoard.getDebugConsole().setVisible(true);
        }
        else {
            gameBoard.getWall().getPlayer().stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        gameBoard.getWall().getPlayer().stop();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point point = mouseEvent.getPoint();
        if(!gameBoard.isShowPauseMenu())
            return;
        if(gameBoard.getContinueButtonRectangle().contains(point)) {
            gameBoard.setShowPauseMenu(false);
            gameBoard.repaint();
        }
        else if(gameBoard.getRestartButtonRectangle().contains(point)) {
            gameBoard.setMessage("RESTARTING GAME...");
            gameBoard.getWall().ballReset();
            gameBoard.getWall().wallReset();
            gameBoard.setShowPauseMenu(false);
            gameBoard.repaint();
        }
        else if(gameBoard.getMenuButtonRectangle().contains(point)) {
            gameBoard.getOwner().dispose();
            gameBoard.getOwner().remove(gameBoard);
            new GameFrame().initialize();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point point = mouseEvent.getPoint();
        if(gameBoard.getMenuButtonRectangle() != null && gameBoard.isShowPauseMenu()) {
            if (gameBoard.getMenuButtonRectangle().contains(point) || gameBoard.getContinueButtonRectangle().contains(point) || gameBoard.getRestartButtonRectangle().contains(point))
                gameBoard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                gameBoard.setCursor(Cursor.getDefaultCursor());
        }
        else {
            gameBoard.setCursor(Cursor.getDefaultCursor());
        }
    }
}
