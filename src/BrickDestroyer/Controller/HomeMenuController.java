package BrickDestroyer.Controller;

import BrickDestroyer.View.GameInfo;
import BrickDestroyer.View.HomeMenu;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class HomeMenuController implements MouseListener, MouseMotionListener {
    private HomeMenu homeMenu;

    private boolean startHovered;
    private boolean exitHovered;
    private boolean infoHovered;

    /**
     * called in HomeMenu
     */
    // HomeMenuController constructor
    public HomeMenuController(HomeMenu homeMenu) {
        this.homeMenu = homeMenu;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point point = mouseEvent.getPoint();
        if(homeMenu.getStartButton().contains(point)) {
            homeMenu.getOwner().enableGameBoard();
        }
        else if(homeMenu.getExitButton().contains(point)) {
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
        else if(homeMenu.getInfoButton().contains(point)) {
            new GameInfo();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startHovered) {
            startHovered = false;
            homeMenu.repaint(homeMenu.getStartButton().x,homeMenu.getStartButton().y,homeMenu.getStartButton().width + 1,homeMenu.getStartButton().height+1);
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

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point point = mouseEvent.getPoint();
        if(homeMenu.getStartButton().contains(point)) {
            startHovered = true;
            homeMenu.repaint(homeMenu.getStartButton().x,homeMenu.getStartButton().y,homeMenu.getStartButton().width + 1,homeMenu.getStartButton().height+1);
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
        else {
            startHovered = false;
            exitHovered = false;
            infoHovered = false;
            homeMenu.repaint(homeMenu.getStartButton().x,homeMenu.getStartButton().y,homeMenu.getStartButton().width + 1,homeMenu.getStartButton().height+1);
            homeMenu.repaint(homeMenu.getExitButton().x, homeMenu.getExitButton().y, homeMenu.getExitButton().width + 1, homeMenu.getExitButton().height+1);
            homeMenu.repaint(homeMenu.getInfoButton().x, homeMenu.getInfoButton().y, homeMenu.getInfoButton().width + 1, homeMenu.getInfoButton().height+1);
            homeMenu.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * all called in HomeMenu
     */
    public boolean isStartHovered() {
        return startHovered;
    }

    public boolean isExitHovered() {
        return exitHovered;
    }

    public boolean isInfoHovered() {
        return infoHovered;
    }
}
