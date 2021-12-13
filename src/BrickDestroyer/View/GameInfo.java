package BrickDestroyer.View;

import BrickDestroyer.Controller.GameInfoController;

import javax.swing.*;
import java.awt.*;

/**
 * to make a JFrame for game info page
 */
public class GameInfo extends JFrame {
    private JLabel GAME_INFO_TITLE;
    private JLabel GAME_INFO_CONTENT;

    private JButton backButton;

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    /**
     * called in HomeMenuController, GameInfo constructor
     */
    public GameInfo() {
        displayInfoTitle();
        displayInfoContent();
        drawBackButton();
        initialize();
        getContentPane().setBackground(new Color(153,0,153));
    }

    /**
     * initialize the frame
     */
    private void initialize () {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(DEF_WIDTH, DEF_HEIGHT);
        this.setLayout(null);
        this.autoLocate();
        this.setVisible(true);
        this.setResizable(false);
        this.add(GAME_INFO_TITLE);
        this.add(GAME_INFO_CONTENT);
        this.add(backButton);
    }

    /**
     * display the title of the page
     */
    private void displayInfoTitle() {
        GAME_INFO_TITLE = new JLabel();

        String gameInfoText = "HOW TO PLAY";

        GAME_INFO_TITLE.setText(gameInfoText);
        GAME_INFO_TITLE.setBounds(225,20,200,20);
        GAME_INFO_TITLE.setFont(new Font("Serif",Font.BOLD,20));
        GAME_INFO_TITLE.setForeground(Color.WHITE);
    }

    /**
     * display the contents of the page
     */
    private void displayInfoContent() {
        GAME_INFO_CONTENT = new JLabel();

        String gameInfoContent = "<html>" +
                "<br/><br/><br/>" +
                "> Press the key \"A\" on your keyboard to move the paddle to the left and \"D\" to move the paddle to the right<br/>" +
                "> Press the space bar on your keyboard to start or pause the game<br/>" +
                "> There will be 2 types of balls in the game, first the rubber ball which is bigger in size slower in speed and second the super ball which is smaller in size faster in speed<br/>" +
                "> Make sure to catch the ball using the paddle, you will only be given 3 balls, if all balls are used up then game over<br/>" +
                "> Press the ESC key on your keyboard to view the PAUSE MENU<br/>" +
                "> Throughout the game there will be 8 different levels in total<br/>" +
                "> If \"TRAINING\" mode is chosen, you can press alt + shift + F1 / fn + alt + shift + F1 on your keyboard to view the DEBUG CONSOLE and skip levels, adjust the ball speed and reset the balls <br/>" +
                "> If \"RANKED\" mode is chosen, you will need to complete as much levels as possible and get higher score within the time limit<br/>" +
                "> In \"RANKED\" mode, breaking a clay brick (brown coloured) awards 1 score, cement brick (dark gray coloured) awards 2 scores, steel brick (light gray coloured) awards 4 scores and magic brick (blue coloured) randomly awards an increment or decrement of 20 scores<br/>" +
                "> By the end of the game, you will be able to submit your name if your score makes it into the HIGH SCORES list<br/>" +
                "</html>";

        GAME_INFO_CONTENT.setText(gameInfoContent);
        GAME_INFO_CONTENT.setBounds(20,10,550,320);
        GAME_INFO_CONTENT.setFont(new Font("Serif",Font.PLAIN,12));
        GAME_INFO_CONTENT.setForeground(Color.WHITE);
    }

    /**
     * draw the back button
     */
    private void drawBackButton() {
        backButton = new JButton("BACK TO MENU");

        backButton.setBounds(200,350,200,40);
        backButton.setFont(new Font("Serif",Font.BOLD,15));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(102,0,102));
        backButton.setFocusable(false);
        backButton.addActionListener(new GameInfoController(this));
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
     * called in GameInfoController, getter
     * @return back button
     */
    public JButton getBackButton() {
        return backButton;
    }
}
