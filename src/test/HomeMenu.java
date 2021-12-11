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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener {
    private static final String GREETINGS = "LETS PLAY";
    private static final String GAME_TITLE = "BRICK DESTROYER";
    private static final String CREDITS = "REFACTORED VERSION 1.0";
    private static final String START_TEXT = "START";
    private static final String EXIT_TEXT = "EXIT";
    private static final String INFO_TEXT = "INFO";

    private static final Color BG_COLOR = new Color(153,0,153);
    private static final Color BORDER_COLOR = new Color(102,0,102);
    //private static final Color DASH_BORDER_COLOR = new Color(255, 216, 0);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = BG_COLOR.brighter();
    private static final int BORDER_SIZE = 10;
    //private static final float[] DASHES = {12,6};

    private static final double BUTTON_DISPLACEMENT = 35.0;

    private Rectangle menuShape;
    private Rectangle startButton;
    private Rectangle exitButton;
    private Rectangle infoButton;

    //private BasicStroke borderStoke;
    private BasicStroke borderStroke;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private GameFrame owner;

    private boolean startHovered;
    private boolean exitHovered;
    private boolean infoHovered;

    BufferedImage homeMenuBackground;

    /**
     *
     * called in GameFrame
     */
    // HomeMenu constructor
    public HomeMenu(GameFrame owner,Dimension area) {
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;

        menuShape = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension buttonDimension = new Dimension(area.width / 3,area.height / 12);
        startButton = new Rectangle(buttonDimension);
        exitButton = new Rectangle(buttonDimension);
        infoButton = new Rectangle(buttonDimension);

        //borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStroke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Monospaced",Font.PLAIN,20);
        gameTitleFont = new Font("Serif",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        buttonFont = new Font("Serif",Font.BOLD,startButton.height - 2);
    }

    /**
     * View
     */
    public void paint(Graphics g) {
        drawMenu((Graphics2D)g);
    }

    /**
     * View
     */
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

    /**
     * View
     */
    private void drawContainer(Graphics2D g2d) {
        try {
            homeMenuBackground = ImageIO.read(new File("src/test/PurpleBrick.jfif"));
            g2d.drawImage(homeMenuBackground,0,0,450,350,null);
        }
        catch(IOException exception) {
            Color previousColor = g2d.getColor();

            g2d.setColor(BG_COLOR);
            g2d.fill(menuShape);

            Stroke tmp = g2d.getStroke();

            g2d.setStroke(borderStroke);
            //g2d.setColor(DASH_BORDER_COLOR);
            //g2d.draw(menuShape);

            //g2d.setStroke(borderStoke);
            g2d.setColor(BORDER_COLOR);
            g2d.draw(menuShape);

            g2d.setStroke(tmp);

            g2d.setColor(previousColor);
        }
    }

    /**
     * View
     */
    private void drawText(Graphics2D g2d) {
        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRectangleText = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRectangleText = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRectangleText = creditsFont.getStringBounds(CREDITS,frc);

        int x = (int)(menuShape.getWidth() - greetingsRectangleText.getWidth()) / 2;
        int y = (int)(menuShape.getHeight() / 4);

        /*g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,x,y);*/

        createText(g2d,greetingsFont,GREETINGS,x,y);

        x = (int)(menuShape.getWidth() - gameTitleRectangleText.getWidth()) / 2;
        y += (int) gameTitleRectangleText.getHeight() * 1.1;  //add 10% of String height between the two strings

        /*g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,x,y);*/

        createText(g2d,gameTitleFont,GAME_TITLE,x,y);

        x = (int)(menuShape.getWidth() - creditsRectangleText.getWidth()) / 2;
        y += (int) creditsRectangleText.getHeight() * 1.1;

        /*g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,x,y);*/

        createText(g2d,creditsFont,CREDITS,x,y);
    }

    /**
     * View
     */
    private void createText(Graphics2D g2d,Font font,String string,int x,int y) {
        g2d.setFont(font);
        g2d.drawString(string,x,y);
    }

    /**
     * View
     */
    private void drawButton(Graphics2D g2d) {
        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D startRectangleText = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D exitRectangleText = buttonFont.getStringBounds(EXIT_TEXT,frc);
        Rectangle2D infoRectangleText = buttonFont.getStringBounds(INFO_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (menuShape.width - startButton.width) / 2;  // align buttons from the right to the center
        int y = (int)((menuShape.height - startButton.height) * 0.6);  // move buttons from the bottom to the current position

        createButton(g2d,startButton,startRectangleText,startHovered,START_TEXT,x,y);

        x = startButton.x;
        y = startButton.y;

        y += BUTTON_DISPLACEMENT;

        createButton(g2d,exitButton,exitRectangleText,exitHovered,EXIT_TEXT,x,y);

        x = exitButton.x;
        y = exitButton.y;

        y += BUTTON_DISPLACEMENT;

        createButton(g2d,infoButton,infoRectangleText,infoHovered,INFO_TEXT,x,y);
    }

    /**
     * View
     */
    private void createButton(Graphics2D g2d,Rectangle button,Rectangle2D rectangleText,boolean buttonHovered,String BUTTON_TEXT,int x,int y) {
        button.setLocation(x,y);

        x = (int)(button.getWidth() - rectangleText.getWidth()) / 2;
        y = (int)(button.getHeight() - rectangleText.getHeight()) / 2;

        x += button.x;
        y += button.y + (button.height * 0.9);

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
     * Controller
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point point = mouseEvent.getPoint();
        if(startButton.contains(point)) {
           owner.enableGameBoard();
        }
        else if(exitButton.contains(point)) {
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
        else if(infoButton.contains(point)) {
            new GameInfo();
        }
    }

    /**
     * Controller
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        /*Point point = mouseEvent.getPoint();
        if(startButton.contains(point)) {
            startClicked = true;
            repaint(startButton.x,startButton.y,startButton.width + 1,startButton.height + 1);
        }
        else if(exitButton.contains(point)) {
            exitClicked = true;
            repaint(exitButton.x, exitButton.y, exitButton.width + 1, exitButton.height + 1);
        }
        else if(infoButton.contains(point)) {
            infoClicked = true;
            repaint(infoButton.x, infoButton.y, infoButton.width + 1, infoButton.height + 1);
        }*/
    }

    /**
     * Controller
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startHovered) {
            startHovered =false;
            repaint(startButton.x,startButton.y,startButton.width + 1,startButton.height+1);
            this.setCursor(Cursor.getDefaultCursor());
        }
        else if(exitHovered) {
            exitHovered = false;
            repaint(exitButton.x, exitButton.y, exitButton.width + 1, exitButton.height+1);
            this.setCursor(Cursor.getDefaultCursor());
        }
        else if(infoHovered) {
            infoHovered = false;
            repaint(infoButton.x, infoButton.y, infoButton.width + 1, infoButton.height+1);
            this.setCursor(Cursor.getDefaultCursor());
        }
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

    /**
     * Controller
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point point = mouseEvent.getPoint();
        if(startButton.contains(point)) {
            startHovered = true;
            repaint(startButton.x,startButton.y,startButton.width + 1,startButton.height+1);
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        else if(exitButton.contains(point)) {
            exitHovered = true;
            repaint(exitButton.x, exitButton.y, exitButton.width + 1, exitButton.height+1);
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        else if(infoButton.contains(point)) {
            infoHovered = true;
            repaint(infoButton.x, infoButton.y, infoButton.width + 1, infoButton.height+1);
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        else {
            startHovered = false;
            exitHovered = false;
            infoHovered = false;
            repaint(startButton.x,startButton.y,startButton.width + 1,startButton.height+1);
            repaint(exitButton.x, exitButton.y, exitButton.width + 1, exitButton.height+1);
            repaint(infoButton.x, infoButton.y, infoButton.width + 1, infoButton.height+1);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
}
