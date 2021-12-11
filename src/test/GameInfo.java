package test;

import javax.swing.*;
import java.awt.*;

public class GameInfo extends JFrame {
    private JLabel GAME_INFO_TITLE;
    private JLabel GAME_INFO_CONTENT;

    private JButton backButton;

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    /**
     * called in HomeMenuController
     */
    // GameInfo constructor
    public GameInfo() {
        displayInfoTitle();
        displayInfoContent();
        drawBackButton();
        initialize();
    }

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

    private void displayInfoTitle() {
        GAME_INFO_TITLE = new JLabel();

        String gameInfoText = "HOW TO PLAY";

        GAME_INFO_TITLE.setText(gameInfoText);
        GAME_INFO_TITLE.setBounds(225,30,200,40);
        GAME_INFO_TITLE.setFont(new Font("Serif",Font.BOLD,20));
    }

    private void displayInfoContent() {
        GAME_INFO_CONTENT = new JLabel();

        String gameInfoContent = "<html>" +
                "<br/><br/><br/>" +
                "<br/>" +
                "<br/>" +
                "<br/>" +
                "<br/>" +
                "<br/>" +
                "<br/>" +
                "<br/>" +
                "</html>";

        GAME_INFO_CONTENT.setText(gameInfoContent);
        GAME_INFO_CONTENT.setBounds(60,50,500,300);
        GAME_INFO_CONTENT.setFont(new Font("Serif",Font.PLAIN,15));
    }

    private void drawBackButton() {
        backButton = new JButton("BACK TO MENU");

        backButton.setBounds(200,350,200,40);
        backButton.setFont(new Font("Serif",Font.BOLD,15));
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(Color.WHITE);
        backButton.setFocusable(false);
        backButton.addActionListener(new GameInfoController(this));
    }

    private void autoLocate() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    /**
     * called in GameInfoController
     */
    public JButton getBackButton() {
        return backButton;
    }
}
