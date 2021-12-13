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

import BrickDestroyer.Controller.GameBoardController;
import BrickDestroyer.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.io.IOException;

/**
 * to make a game board using JComponent
 */
public class GameBoard extends JComponent {
    private static final String CONTINUE = "CONTINUE";
    private static final String RESTART = "RESTART";
    private static final String MENU = "BACK TO MENU";
    private static final String PAUSE = "PAUSE MENU";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(255,0,255);;

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private Timer gameTimer;
    private Wall wall;
    private String message;

    private String score;
    private boolean showPauseMenu;
    private Font menuFont;

    private Rectangle continueButtonRectangle;
    private Rectangle menuButtonRectangle;
    private Rectangle restartButtonRectangle;
    private int stringLength;

    private DebugConsole debugConsole;

    private GameFrame owner;
    private String mode;

    private GameTimer timer;

    /**
     * called in GameFrame, GameBoard constructor
     * @param owner current owner
     * @param mode current mode
     */
    public GameBoard(GameFrame owner,String mode) {
        super();

        this.owner = owner;
        this.mode = mode;
        timer = new GameTimer();

        stringLength = 0;
        showPauseMenu = false;

        menuFont = new Font("Serif",Font.BOLD,TEXT_SIZE);

        this.initialize();
        message = "";
        score = "";
        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),6/2,new Point(300,430));

        debugConsole = new DebugConsole(owner,wall,this);
        //initialize the first level
        wall.nextLevel();

        gameTimer = new Timer(10,e -> {
            timer.setGameStatus(true);
            wall.move();
            wall.findImpacts();
            message = "";
            score = "";

            if(this.mode == "training") {
                message = "BRICKS: " + wall.getBrickCount() + "   " + "BALLS: " + wall.getBallCount();
            }
            else {
                score = "BRICKS: " + wall.getBrickCount() + "   " + "BALLS: " + wall.getBallCount() + "   " + "TOTAL SCORE: " + wall.getTotalScore() + "   " + "REMAINING TIME: " + timer.getDdMinute() + ":" + timer.getDdSecond();
            }

            if(timer.getMinute() == 0 && timer.getSecond() == 0) {
                gameTimer.stop();
                timer.setGameStatus(false);
                message = "GAME OVER";

                try {
                    if (GameHighScore.Check(wall.getTotalScore(), timer.getDdMinute() + ":" + timer.getDdSecond()) == true) {
                        JFrame popup = new JFrame();

                        String userName = (String)JOptionPane.showInputDialog(
                                popup, "New High Score!\n"
                                        + "Name:",
                                "High Score",
                                JOptionPane.PLAIN_MESSAGE
                        );

                        if (userName != null){
                            GameHighScore.AddPlayer(userName,wall.getTotalScore(),timer.getDdMinute() + ":" + timer.getDdSecond());
                        }
                    }
                    owner.enableGameHighScore();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }

            if(wall.isBallLost()) {
                wall.ballReset();
                gameTimer.stop();

                if(wall.ballEnd()) {
                    wall.wallReset();
                    message = "GAME OVER";
                    if(this.mode != "training") {
                        try {
                            if (GameHighScore.Check(wall.getTotalScore(), timer.getDdMinute() + ":" + timer.getDdSecond()) == true) {
                                JFrame popup = new JFrame();

                                String userName = (String)JOptionPane.showInputDialog(
                                        popup, "New High Score!\n"
                                                + "Name:",
                                        "High Score",
                                        JOptionPane.PLAIN_MESSAGE
                                );

                                if (userName != null){
                                    GameHighScore.AddPlayer(userName,wall.getTotalScore(),timer.getDdMinute() + ":" + timer.getDdSecond());
                                }
                            }
                            owner.enableGameHighScore();
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                    }
                    score = "";
                    wall.setTotalScore(0);
                }
            }
            else if(wall.isDone()) {
                if(wall.hasLevel()) {
                    message = "GO TO NEXT LEVEL";
                    score = "";
                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    wall.nextLevel();
                }
                else {
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();

                    if(this.mode != "training") {
                        try {
                            if (GameHighScore.Check(wall.getTotalScore(), timer.getDdMinute() + ":" + timer.getDdSecond()) == true) {
                                JFrame popup = new JFrame();

                                String userName = (String)JOptionPane.showInputDialog(
                                        popup, "New High Score!\n"
                                                + "Name:",
                                        "High Score",
                                        JOptionPane.PLAIN_MESSAGE
                                );

                                if (userName != null){
                                    GameHighScore.AddPlayer(userName,wall.getTotalScore(),timer.getDdMinute() + ":" + timer.getDdSecond());
                                    owner.enableGameHighScore();
                                }
                            }
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                    }
                    score = "";
                    wall.setTotalScore(0);
                }
            }
            repaint();
        });
    }

    /**
     * initialize the game board
     */
    private void initialize() {
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        GameBoardController gameBoardController = new GameBoardController(this);
        this.addKeyListener(gameBoardController);
        this.addMouseListener(gameBoardController);
        this.addMouseMotionListener(gameBoardController);
    }

    /**
     * to paint the game board
     * @param g used for graphics
     */
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(message,240,225);
        g2d.drawString(score,120,225);

        drawBall(wall.getBall(),g2d);

        for(Brick brick : wall.getBricks())
            if(!brick.isBroken())
                drawBrick(brick,g2d);

        drawPlayer(wall.getPlayer(),g2d);

        if(showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * to temporarily clear the colours
     * @param g2d used for 2D graphics
     */
    private void clear(Graphics2D g2d) {
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    /**
     * to draw the brick
     * @param brick current brick
     * @param g2d used for 2D graphics
     */
    private void drawBrick(Brick brick,Graphics2D g2d) {
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrickShape());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrickShape());

        g2d.setColor(tmp);
    }

    /**
     * to draw the ball
     * @param ball current ball
     * @param g2d used for 2D graphics
     */
    private void drawBall(Ball ball, Graphics2D g2d) {
        Color tmp = g2d.getColor();

        Shape shape = ball.getBallShape();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(shape);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(shape);

        g2d.setColor(tmp);
    }

    /**
     * to draw the player
     * @param player current player
     * @param g2d used for 2D graphics
     */
    private void drawPlayer(Player player, Graphics2D g2d) {
        Color tmp = g2d.getColor();

        Shape shape = player.getPlayerShape();

        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(shape);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(shape);

        g2d.setColor(tmp);
    }

    /**
     * to draw the menu
     * @param g2d used for 2D graphics
     */
    private void drawMenu(Graphics2D g2d) {
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * to create the obscure game board
     * @param g2d used for 2D graphics
     */
    private void obscureGameBoard(Graphics2D g2d) {
        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(alphaComposite);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * to draw the pause menu
     * @param g2d used for 2D graphics
     */
    private void drawPauseMenu(Graphics2D g2d) {
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();

        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(stringLength == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            stringLength = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - stringLength) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;

        if(continueButtonRectangle == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRectangle = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRectangle.setLocation(x,y - continueButtonRectangle.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRectangle == null){
            restartButtonRectangle = (Rectangle) continueButtonRectangle.clone();
            restartButtonRectangle.setLocation(x,y - restartButtonRectangle.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0 / 2;

        if(menuButtonRectangle == null){
            menuButtonRectangle = (Rectangle) continueButtonRectangle.clone();
            menuButtonRectangle.setLocation(x,y - menuButtonRectangle.height);
        }

        g2d.drawString(MENU,x,y);

        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    /**
     * called in GameFrameController, to perform some actions if is on lost focus
     */
    public void onLostFocus() {
        gameTimer.stop();
        message = "FOCUS LOST";
        score = "";
        repaint();
    }

    /**
     * called in GameBoardController, getter
     * @return current wall
     */
    public Wall getWall() {
        return wall;
    }

    /**
     * called in GameBoardController, getter
     * @return if pause menu is shown
     */
    public boolean isShowPauseMenu() {
        return showPauseMenu;
    }

    /**
     * called in GameBoardController, setter
     * @param showPauseMenu pause menu is shown or not
     */
    public void setShowPauseMenu(boolean showPauseMenu) {
        this.showPauseMenu = showPauseMenu;
    }

    /**
     * called in GameBoardController, getter
     * @return current rectangle of continue button
     */
    public Rectangle getContinueButtonRectangle() {
        return continueButtonRectangle;
    }

    /**
     * called in GameBoardController, getter
     * @return current rectangle of menu button
     */
    public Rectangle getMenuButtonRectangle() {
        return menuButtonRectangle;
    }

    /**
     * called in GameBoardController, getter
     * @return current rectangle of restart button
     */
    public Rectangle getRestartButtonRectangle() {
        return restartButtonRectangle;
    }

    /**
     * called in GameBoardController, getter
     * @return current game timer
     */
    public Timer getGameTimer() {
        return gameTimer;
    }

    /**
     * called in GameBoardController, getter
     * @return current debug console
     */
    public DebugConsole getDebugConsole() {
        return debugConsole;
    }

    /**
     * called in GameBoardController, getter
     * @return current owner
     */
    public GameFrame getOwner() {
        return owner;
    }

    /**
     * called in GameBoardController, getter
     * @return current mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * called in GameBoardController, setter
     * @param mode current mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * called in GameBoardController, setter
     * @param score current score
     */
    public void setScore(String score) {
        this.score = score;
    }

    /**
     * called in GameBoardController, getter
     * @return current timer
     */
    public GameTimer getTimer() {
        return timer;
    }
}
