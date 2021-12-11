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
package test;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

public class GameFrame extends JFrame {
    private static final String DEF_TITLE = "BRICK DESTROYER";

    private GameBoard gameBoard;
    private HomeMenu homeMenu;

    private boolean gaming;

    /**
     * View
     * called in GameInfo & GraphicsMain
     */
    // GameFrame constructor
    public GameFrame() {
        super();
        gaming = false;
        this.setLayout(new BorderLayout());
        gameBoard = new GameBoard(this);
        homeMenu = new HomeMenu(this,new Dimension(450,350));
        this.add(homeMenu,BorderLayout.CENTER);
        this.setUndecorated(true);
    }

    /**
     * called in GraphicsMain
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
     * called in HomeMenu
     */
    public void enableGameBoard() {
        this.dispose();
        this.remove(homeMenu);
        this.add(gameBoard,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        this.addWindowFocusListener(new GameFrameController(this));
    }

    private void autoLocate() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    /**
     * called in GameFrameController
     */
    public boolean isGaming() {
        return gaming;
    }

    /**
     * called in GameFrameController
     */
    public void setGaming(boolean gaming) {
        this.gaming = gaming;
    }

    /**
     * called in GameFrameController
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
