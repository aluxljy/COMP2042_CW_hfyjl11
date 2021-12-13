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

import BrickDestroyer.Model.Wall;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * to make a JPanel for debug panel
 */
public class DebugPanel extends JPanel {
    private static final Color DEF_BKG = Color.WHITE;

    private JButton skipLevel;
    private JButton resetBalls;

    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    private Wall wall;

    /**
     * called in DebugConsole, DebugPanel constructor
     * @param wall current wall
     */
    public DebugPanel(Wall wall) {
        this.wall = wall;

        initialize();

        skipLevel = makeButton("Skip Level",e -> wall.nextLevel());
        resetBalls = makeButton("Reset Balls",e -> wall.resetBallCount());

        ballXSpeed = makeSlider(-4,4,e -> wall.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(-4,4,e -> wall.setBallYSpeed(ballYSpeed.getValue()));

        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);
    }

    /**
     * initialize the debug panel
     */
    private void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    /**
     * to make a button and add a listener to the button
     * @param title button title
     * @param listener action listener
     * @return resultant output
     */
    private JButton makeButton(String title, ActionListener listener){
        JButton out = new JButton(title);
        out.addActionListener(listener);
        return out;
    }

    /**
     * to make a slider and add a listener to the slider
     * @param min minimum value of the slider
     * @param max maximum value of the slider
     * @param listener change listener
     * @return resultant output
     */
    private JSlider makeSlider(int min, int max, ChangeListener listener){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(listener);
        return out;
    }

    /**
     * called in DebugConsoleController, to set the speed of the ball
     * @param x speed x of the ball
     * @param y speed y of the ball
     */
    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }
}
