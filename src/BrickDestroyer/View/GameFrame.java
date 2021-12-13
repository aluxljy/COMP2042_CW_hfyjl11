/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package BrickDestroyer.View;

import BrickDestroyer.Controller.GameFrameController;

import javax.swing.*;
import java.awt.*;

/**
 * to make a JFrame for game frame
 */
public class GameFrame extends JFrame {
    private static final String DEF_TITLE = "BRICK DESTROYER";

    private GameBoard gameBoard;
    private HomeMenu homeMenu;

    private boolean gaming;

    private String currentFrame;
    private GameHighScoreFrame gameHighScoreFrame;

    /**
     * called in GameBoardController, GameHighScoreController, GameInfoController & GraphicsMain, GameFrame constructor
     */
    public GameFrame() {
        super();
        gameHighScoreFrame = new GameHighScoreFrame(this);
        gaming = false;
        this.setLayout(new BorderLayout());
        homeMenu = new HomeMenu(this,new Dimension(450,400));
        this.add(homeMenu,BorderLayout.CENTER);
        this.setUndecorated(true);
    }

    /**
     * called in GraphicsMain, GameBoardController & GameHighScoreController, initialize the frame
     */
    public void initialize() {
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
        this.setResizable(false);
    }

    /**
     * called in GameBoardController & HomeMenuController, to initialize and enable the game board
     * @param mode current mode
     * @param currentFrame current game frame
     */
    public void enableGameBoard(String mode,String currentFrame) {
        this.currentFrame = currentFrame;
        this.dispose();
        if(currentFrame == "home menu") {
            this.remove(homeMenu);
        }
        else {
            this.remove(gameBoard);
        }
        gameBoard = new GameBoard(this,mode);
        this.add(gameBoard,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        this.addWindowFocusListener(new GameFrameController(this));
    }

    /**
     * called in HomeMenuController & GameBoard, to initialize and enable the game high score
     */
    public void enableGameHighScore() {
        this.dispose();
        gameHighScoreFrame.initialize();
    }

    /**
     * auto locate the position of the frame
     */
    private void autoLocate() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    /**
     * called in GameFrameController, getter
     * @return if the player is gaming or not
     */
    public boolean isGaming() {
        return gaming;
    }

    /**
     * called in GameFrameController, setter
     * @param gaming player is gaming or not
     */
    public void setGaming(boolean gaming) {
        this.gaming = gaming;
    }

    /**
     * called in GameFrameController, getter
     * @return current game board
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * called in GameHighScoreController, getter
     * @return current game high score frame
     */
    public GameHighScoreFrame getGameHighScoreFrame() {
        return gameHighScoreFrame;
    }
}
