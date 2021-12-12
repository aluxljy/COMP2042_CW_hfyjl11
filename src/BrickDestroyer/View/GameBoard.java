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
import BrickDestroyer.Model.Ball;
import BrickDestroyer.Model.Brick;
import BrickDestroyer.Model.Player;
import BrickDestroyer.Model.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;

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

    private JFrame owner;
    private String mode;

    /**
     * called in GameFrame
     */
    // GameBoard constructor
    public GameBoard(JFrame owner,String mode) {
        super();

        this.owner = owner;
        this.mode = mode;

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
            wall.move();
            wall.findImpacts();
            message = "";
            score = "";

            if(this.mode == "training") {
                message = "BRICKS: " + wall.getBrickCount() + "   " + "BALLS: " + wall.getBallCount();
            }
            else {
                score = "BRICKS: " + wall.getBrickCount() + "   " + "BALLS: " + wall.getBallCount() + "   " + "TOTAL SCORE: " + wall.getTotalScore();
            }

            if(wall.isBallLost()) {
                if(wall.ballEnd()) {
                    wall.wallReset();
                    message = "GAME OVER";
                    score = "";
                    wall.setTotalScore(0);
                }
                wall.ballReset();
                gameTimer.stop();
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
                    score = "";
                    gameTimer.stop();
                    wall.setTotalScore(0);
                }
            }
            repaint();
        });
    }

    private void initialize() {
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        GameBoardController gameBoardController = new GameBoardController(this);
        this.addKeyListener(gameBoardController);
        this.addMouseListener(gameBoardController);
        this.addMouseMotionListener(gameBoardController);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(message,240,225);
        g2d.drawString(score,190,225);

        drawBall(wall.getBall(),g2d);

        for(Brick brick : wall.getBricks())
            if(!brick.isBroken())
                drawBrick(brick,g2d);

        drawPlayer(wall.getPlayer(),g2d);

        if(showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    private void clear(Graphics2D g2d) {
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    private void drawBrick(Brick brick,Graphics2D g2d) {
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrickShape());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrickShape());

        g2d.setColor(tmp);
    }

    private void drawBall(Ball ball, Graphics2D g2d) {
        Color tmp = g2d.getColor();

        Shape shape = ball.getBallShape();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(shape);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(shape);

        g2d.setColor(tmp);
    }

    private void drawPlayer(Player player, Graphics2D g2d) {
        Color tmp = g2d.getColor();

        Shape shape = player.getPlayerShape();

        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(shape);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(shape);

        g2d.setColor(tmp);
    }

    private void drawMenu(Graphics2D g2d) {
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

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
     * called in GameFrameController
     */
    public void onLostFocus() {
        gameTimer.stop();
        message = "FOCUS LOST";
        score = "";
        repaint();
    }

    /**
     * all called in GameBoardController
     */
    public Wall getWall() {
        return wall;
    }

    public boolean isShowPauseMenu() {
        return showPauseMenu;
    }

    public void setShowPauseMenu(boolean showPauseMenu) {
        this.showPauseMenu = showPauseMenu;
    }

    public Rectangle getContinueButtonRectangle() {
        return continueButtonRectangle;
    }

    public Rectangle getMenuButtonRectangle() {
        return menuButtonRectangle;
    }

    public Rectangle getRestartButtonRectangle() {
        return restartButtonRectangle;
    }

    public Timer getGameTimer() {
        return gameTimer;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DebugConsole getDebugConsole() {
        return debugConsole;
    }

    public JFrame getOwner() {
        return owner;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
