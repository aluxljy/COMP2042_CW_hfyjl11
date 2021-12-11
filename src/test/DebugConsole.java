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

public class DebugConsole extends JDialog {
    private static final String TITLE = "DEBUG CONSOLE";

    private JFrame owner;
    private DebugPanel debugPanel;

    private GameBoard gameBoard;
    private Wall wall;

    /**
     * called in GameBoard
     */
    // DebugConsole constructor
    public DebugConsole(JFrame owner,Wall wall,GameBoard gameBoard) {
        this.wall = wall;
        this.owner = owner;
        this.gameBoard = gameBoard;

        initialize();

        debugPanel = new DebugPanel(wall);
        this.add(debugPanel,BorderLayout.CENTER);

        this.pack();
    }

    private void initialize() {
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(new DebugConsoleController(this));
        this.setFocusable(true);
    }

    /**
     * all called in DebugConsoleController
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    @Override
    public JFrame getOwner() {
        return owner;
    }

    public DebugPanel getDebugPanel() {
        return debugPanel;
    }

    public Wall getWall() {
        return wall;
    }
}
