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

import BrickDestroyer.Controller.HomeMenuController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomeMenu extends JComponent {
    private static final String GREETINGS = "LETS PLAY";
    private static final String GAME_TITLE = "BRICK DESTROYER";
    private static final String CREDITS = "REFACTORED VERSION 1.0";
    private static final String TRAINING_TEXT = "TRAINING";
    private static final String EXIT_TEXT = "EXIT";
    private static final String INFO_TEXT = "INFO";
    private static final String HIGH_SCORES_TEXT = "HIGH SCORES";
    private static final String RANKED_TEXT = "RANKED";

    private static final Color BG_COLOR = new Color(153,0,153);
    private static final Color BORDER_COLOR = new Color(102,0,102);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = BG_COLOR.brighter();
    private static final int BORDER_SIZE = 10;

    private static final double BUTTON_DISPLACEMENT = 45.0;

    private Rectangle menuShape;
    private Rectangle trainingButton;
    private Rectangle exitButton;
    private Rectangle infoButton;
    private Rectangle highScoresButton;
    private Rectangle rankedButton;

    private BasicStroke borderStroke;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private GameFrame owner;

    BufferedImage homeMenuBackground;

    private HomeMenuController homeMenuController;

    /**
     * called in GameFrame
     */
    // HomeMenu constructor
    public HomeMenu(GameFrame owner,Dimension area) {
        this.setFocusable(true);
        this.requestFocusInWindow();
        HomeMenuController homeMenuController = new HomeMenuController(this);
        this.addMouseListener(homeMenuController);
        this.addMouseMotionListener(homeMenuController);

        this.owner = owner;

        menuShape = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension buttonDimension = new Dimension(area.width * 3 / 7,area.height / 14);
        trainingButton = new Rectangle(buttonDimension);
        exitButton = new Rectangle(buttonDimension);
        infoButton = new Rectangle(buttonDimension);
        rankedButton = new Rectangle(buttonDimension);
        highScoresButton = new Rectangle(buttonDimension);

        borderStroke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Monospaced",Font.PLAIN,20);
        gameTitleFont = new Font("Serif",Font.BOLD,42);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        buttonFont = new Font("Serif",Font.BOLD,trainingButton.height - 3);

        this.homeMenuController = homeMenuController;
    }

    public void paint(Graphics g) {
        drawMenu((Graphics2D)g);
    }

    private void drawMenu(Graphics2D g2d) {
        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color previousColor = g2d.getColor();
        Font previousFont = g2d.getFont();

        double x = menuShape.getX();
        double y = menuShape.getY();

        g2d.translate(x,y);

        //method call
        drawText(g2d);
        drawButton(g2d);
        //end of method call

        g2d.translate(-x,-y);
        g2d.setFont(previousFont);
        g2d.setColor(previousColor);
    }

    private void drawContainer(Graphics2D g2d) {
        try {
            homeMenuBackground = ImageIO.read(new File("src/BrickDestroyer/View/Image/PurpleBrick.jfif"));
            g2d.drawImage(homeMenuBackground,0,0,450,400,null);
        }
        catch(IOException exception) {
            Color previousColor = g2d.getColor();

            g2d.setColor(BG_COLOR);
            g2d.fill(menuShape);

            Stroke tmp = g2d.getStroke();

            g2d.setStroke(borderStroke);

            g2d.setColor(BORDER_COLOR);
            g2d.draw(menuShape);

            g2d.setStroke(tmp);

            g2d.setColor(previousColor);
        }
    }

    private void drawText(Graphics2D g2d) {
        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRectangleText = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRectangleText = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRectangleText = creditsFont.getStringBounds(CREDITS,frc);

        int x = (int)(menuShape.getWidth() - greetingsRectangleText.getWidth()) / 2;
        int y = (int)(menuShape.getHeight() / 6);

        createText(g2d,greetingsFont,GREETINGS,x,y);

        x = (int)(menuShape.getWidth() - gameTitleRectangleText.getWidth()) / 2;
        y += (int) gameTitleRectangleText.getHeight() * 1.1;  //add 10% of String height between the two strings

        createText(g2d,gameTitleFont,GAME_TITLE,x,y);

        x = (int)(menuShape.getWidth() - creditsRectangleText.getWidth()) / 2;
        y += (int) creditsRectangleText.getHeight() * 1.1;

        createText(g2d,creditsFont,CREDITS,x,y);
    }

    private void createText(Graphics2D g2d,Font font,String string,int x,int y) {
        g2d.setFont(font);
        g2d.drawString(string,x,y);
    }

    private void drawButton(Graphics2D g2d) {
        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D trainingRectangleText = buttonFont.getStringBounds(TRAINING_TEXT,frc);
        Rectangle2D exitRectangleText = buttonFont.getStringBounds(EXIT_TEXT,frc);
        Rectangle2D infoRectangleText = buttonFont.getStringBounds(INFO_TEXT,frc);
        Rectangle2D rankedRectangleText = buttonFont.getStringBounds(RANKED_TEXT,frc);
        Rectangle2D highScoresRectangleText = buttonFont.getStringBounds(HIGH_SCORES_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (menuShape.width - trainingButton.width) / 2;  // align buttons from the right to the center
        int y = (int)((menuShape.height - trainingButton.height) * 0.45);  // move buttons from the bottom to the current position

        createButton(g2d,trainingButton,trainingRectangleText,homeMenuController.isStartHovered(),TRAINING_TEXT,x,y);

        x = trainingButton.x;
        y = trainingButton.y;

        y += BUTTON_DISPLACEMENT;

        createButton(g2d,rankedButton,rankedRectangleText,homeMenuController.isRankedHovered(),RANKED_TEXT,x,y);

        x = rankedButton.x;
        y = rankedButton.y;

        y += BUTTON_DISPLACEMENT;

        createButton(g2d,highScoresButton,highScoresRectangleText,homeMenuController.isHighScoresHovered(),HIGH_SCORES_TEXT,x,y);

        x = highScoresButton.x;
        y = highScoresButton.y;

        y += BUTTON_DISPLACEMENT;

        createButton(g2d,infoButton,infoRectangleText,homeMenuController.isInfoHovered(),INFO_TEXT,x,y);

        x = infoButton.x;
        y = infoButton.y;

        y += BUTTON_DISPLACEMENT;

        createButton(g2d,exitButton,exitRectangleText,homeMenuController.isExitHovered(),EXIT_TEXT,x,y);
    }

    private void createButton(Graphics2D g2d,Rectangle button,Rectangle2D rectangleText,boolean buttonHovered,String BUTTON_TEXT,int x,int y) {
        button.setLocation(x,y);

        x = (int)(button.getWidth() - rectangleText.getWidth()) / 2;
        y = (int)(button.getHeight() - rectangleText.getHeight()) / 2;

        x += button.x;
        y += button.y + (button.height * 0.86);  // button height position

        if(buttonHovered) {
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(button);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(BUTTON_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else {
            g2d.draw(button);
            g2d.drawString(BUTTON_TEXT,x,y);
        }
    }

    /**
     * all called in HomeMenuController
     */
    public Rectangle getTrainingButton() {
        return trainingButton;
    }

    public Rectangle getExitButton() {
        return exitButton;
    }

    public Rectangle getInfoButton() {
        return infoButton;
    }

    public GameFrame getOwner() {
        return owner;
    }

    public Rectangle getHighScoresButton() {
        return highScoresButton;
    }

    public Rectangle getRankedButton() {
        return rankedButton;
    }
}
