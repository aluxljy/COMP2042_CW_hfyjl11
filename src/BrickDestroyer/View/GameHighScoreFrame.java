package BrickDestroyer.View;

import javax.swing.*;

import BrickDestroyer.Controller.GameHighScoreController;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.awt.*;
import java.io.IOException;

/**
 * to create a JFrame for game high score page
 */
public class GameHighScoreFrame extends JFrame {
    private static final String DEF_TITLE = "BRICK DESTROYER";
    private JFXPanel jfxPanel;
    private GameFrame gameFrame;

    /**
     * called in GameFrame, GameHighScoreFrame
     * @param gameFrame current game frame
     */
    public GameHighScoreFrame(GameFrame gameFrame){
        super();

        jfxPanel = new JFXPanel();

        this.gameFrame = gameFrame;
        this.setLayout(new BorderLayout());
        this.setUndecorated(false);

        Platform.runLater(() -> {
            initGameHighScoreFX(jfxPanel);
        });
    }

    /**
     * called in GameFrame, initialize the frame
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
     * auto locate the position of the frame
     */
    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    /**
     * to initialize the JFXPanel
     * @param jfxPanel current jfxPanel
     */
    private void initGameHighScoreFX(JFXPanel jfxPanel){

        this.add(jfxPanel, BorderLayout.CENTER);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GameFrame.class.getResource("GameHighscore.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            GameHighScoreController gameHighScoreController = fxmlLoader.getController();

            gameHighScoreController.setGameFrame(gameFrame);

            jfxPanel.setScene(scene);
        }
        catch (IOException exception) {
            exception.printStackTrace();
            System.exit(0);
        }
    }
}
