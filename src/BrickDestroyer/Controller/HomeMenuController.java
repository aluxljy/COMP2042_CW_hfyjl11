package BrickDestroyer.Controller;

import BrickDestroyer.View.GameInfo;
import BrickDestroyer.View.HomeMenu;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Controller for HomeMenu
 */
public class HomeMenuController implements MouseListener, MouseMotionListener {
    private HomeMenu homeMenu;

    private boolean startHovered;
    private boolean exitHovered;
    private boolean infoHovered;
    private boolean rankedHovered;
    private boolean highScoresHovered;

    /**
     * called in HomeMenu, HomeMenuController constructor
     * @param homeMenu current home menu
     */
    public HomeMenuController(HomeMenu homeMenu) {
        this.homeMenu = homeMenu;
    }

    /**
     * to get the point the mouse clicks on and perform actions based on the point clicked
     * @param mouseEvent event of mouse
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point point = mouseEvent.getPoint();
        if(homeMenu.getTrainingButton().contains(point)) {
            homeMenu.getOwner().enableGameBoard("training","home menu");
        }
        else if(homeMenu.getRankedButton().contains(point)) {
            homeMenu.getOwner().enableGameBoard("ranked","home menu");
        }
        else if(homeMenu.getExitButton().contains(point)) {
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
        else if(homeMenu.getInfoButton().contains(point)) {
            new GameInfo();
        }
        else if(homeMenu.getHighScoresButton().contains(point)) {
            homeMenu.getOwner().enableGameHighScore();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    /**
     * to get the point the mouse releases from and perform actions based on the point of release
     * @param mouseEvent event of mouse
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startHovered) {
            startHovered = false;
            homeMenu.repaint(homeMenu.getTrainingButton().x,homeMenu.getTrainingButton().y,homeMenu.getTrainingButton().width + 1,homeMenu.getTrainingButton().height+1);
            homeMenu.setCursor(Cursor.getDefaultCursor());
        }
        else if(exitHovered) {
            exitHovered = false;
            homeMenu.repaint(homeMenu.getExitButton().x, homeMenu.getExitButton().y, homeMenu.getExitButton().width + 1, homeMenu.getExitButton().height+1);
            homeMenu.setCursor(Cursor.getDefaultCursor());
        }
        else if(infoHovered) {
            infoHovered = false;
            homeMenu.repaint(homeMenu.getInfoButton().x, homeMenu.getInfoButton().y, homeMenu.getInfoButton().width + 1, homeMenu.getInfoButton().height+1);
            homeMenu.setCursor(Cursor.getDefaultCursor());
        }
        else if(rankedHovered) {
            rankedHovered = false;
            homeMenu.repaint(homeMenu.getRankedButton().x, homeMenu.getRankedButton().y, homeMenu.getRankedButton().width + 1, homeMenu.getRankedButton().height+1);
            homeMenu.setCursor(Cursor.getDefaultCursor());
        }
        else if(highScoresHovered) {
            highScoresHovered = false;
            homeMenu.repaint(homeMenu.getHighScoresButton().x, homeMenu.getHighScoresButton().y, homeMenu.getHighScoresButton().width + 1, homeMenu.getHighScoresButton().height+1);
            homeMenu.setCursor(Cursor.getDefaultCursor());
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
     * to get the point the mouse hovers on and perform actions based on the point hovered
     * @param mouseEvent event of mouse
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point point = mouseEvent.getPoint();
        if(homeMenu.getTrainingButton().contains(point)) {
            startHovered = true;
            homeMenu.repaint(homeMenu.getTrainingButton().x,homeMenu.getTrainingButton().y,homeMenu.getTrainingButton().width + 1,homeMenu.getTrainingButton().height+1);
            homeMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        else if(homeMenu.getExitButton().contains(point)) {
            exitHovered = true;
            homeMenu.repaint(homeMenu.getExitButton().x, homeMenu.getExitButton().y, homeMenu.getExitButton().width + 1, homeMenu.getExitButton().height+1);
            homeMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        else if(homeMenu.getInfoButton().contains(point)) {
            infoHovered = true;
            homeMenu.repaint(homeMenu.getInfoButton().x, homeMenu.getInfoButton().y, homeMenu.getInfoButton().width + 1, homeMenu.getInfoButton().height+1);
            homeMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        else if(homeMenu.getRankedButton().contains(point)) {
            rankedHovered = true;
            homeMenu.repaint(homeMenu.getRankedButton().x, homeMenu.getRankedButton().y, homeMenu.getRankedButton().width + 1, homeMenu.getRankedButton().height+1);
            homeMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        else if(homeMenu.getHighScoresButton().contains(point)) {
            highScoresHovered = true;
            homeMenu.repaint(homeMenu.getHighScoresButton().x, homeMenu.getHighScoresButton().y, homeMenu.getHighScoresButton().width + 1, homeMenu.getHighScoresButton().height+1);
            homeMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        else {
            startHovered = false;
            exitHovered = false;
            infoHovered = false;
            rankedHovered = false;
            highScoresHovered = false;
            homeMenu.repaint(homeMenu.getTrainingButton().x,homeMenu.getTrainingButton().y,homeMenu.getTrainingButton().width + 1,homeMenu.getTrainingButton().height+1);
            homeMenu.repaint(homeMenu.getExitButton().x, homeMenu.getExitButton().y, homeMenu.getExitButton().width + 1, homeMenu.getExitButton().height+1);
            homeMenu.repaint(homeMenu.getInfoButton().x, homeMenu.getInfoButton().y, homeMenu.getInfoButton().width + 1, homeMenu.getInfoButton().height+1);
            homeMenu.repaint(homeMenu.getRankedButton().x, homeMenu.getRankedButton().y, homeMenu.getRankedButton().width + 1, homeMenu.getRankedButton().height+1);
            homeMenu.repaint(homeMenu.getHighScoresButton().x, homeMenu.getHighScoresButton().y, homeMenu.getHighScoresButton().width + 1, homeMenu.getHighScoresButton().height+1);
            homeMenu.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * called in HomeMenu
     * @return if the current start button is hovered, getter
     */
    public boolean isStartHovered() {
        return startHovered;
    }

    /**
     * called in HomeMenu
     * @return if the current exit button is hovered, getter
     */
    public boolean isExitHovered() {
        return exitHovered;
    }

    /**
     * called in HomeMenu
     * @return if the current info button is hovered, getter
     */
    public boolean isInfoHovered() {
        return infoHovered;
    }

    /**
     * called in HomeMenu
     * @return if the current ranked button is hovered, getter
     */
    public boolean isRankedHovered() {
        return rankedHovered;
    }

    /**
     * called in HomeMenu
     * @return if the current high scores button is hovered, getter
     */
    public boolean isHighScoresHovered() {
        return highScoresHovered;
    }
}
