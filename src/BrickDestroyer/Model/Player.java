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
package BrickDestroyer.Model;

import java.awt.*;

/**
 * to make the features of a player
 */
public class Player {
    public static final Color BORDER_COLOR = Color.GRAY;
    public static final Color INNER_COLOR = Color.LIGHT_GRAY;

    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerShape;

    private Point ballPosition;
    private int moveAmount;
    private int min;
    private int max;

    /**
     * called in Wall, the Player constructor takes in some parameters to make the shape of the player and set the colour of the player
     * @param ballPosition position of the ball
     * @param width width of the player
     * @param height height of the player
     * @param container rectangular shape of the player
     */
    public Player(Point ballPosition,int width,int height,Rectangle container) {
        this.ballPosition = ballPosition;
        moveAmount = 0;
        playerShape = makeRectangle(width,height);
        min = container.x + (width / 2);  // left boundary
        max = min + container.width - width;  // right boundary
    }

    /**
     * to make the shape of the player
     * @param width width of the player
     * @param height height of the player
     * @return rectangular shape of the player
     */
    private Rectangle makeRectangle(int width,int height) {
        Point point = new Point((int)(ballPosition.getX() - (width / 2)),(int) ballPosition.getY() + (height / 3));  // position of the player
        return new Rectangle(point,new Dimension(width,height));
    }

    /**
     * called in Wall
     * @param ball the current ball
     * @return if the player has impact with the ball
     */
    public boolean impactWithBall(Ball ball) {
        return playerShape.contains(ball.getBallPosition()) && playerShape.contains(ball.getDown());
    }

    /**
     * called in Wall, to set the location of the player when it is moving
     */
    public void move() {
        double x = ballPosition.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPosition.setLocation(x, ballPosition.getY());
        playerShape.setLocation(ballPosition.x - (int) playerShape.getWidth() / 2, ballPosition.y + (int) playerShape.getHeight() / 3);
    }

    /**
     * called in GameBoardController, to move the player to the left
     */
    public void moveLeft() {
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * called in GameBoardController, to move the player to the right
     */
    public void moveRight() {
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * called in GameBoardController, to stop the player
     */
    public void stop() {
        moveAmount = 0;
    }

    /**
     * called in GameBoard, getter
     * @return shape of the player
     */
    public Shape getPlayerShape() {
        return playerShape;
    }

    /**
     * called in Wall, to set the location of the player when it is moved to a certain position
     * @param position desired position to move the player to
     */
    public void moveTo(Point position) {
        ballPosition.setLocation(position);
        playerShape.setLocation(ballPosition.x - (int) playerShape.getWidth() / 2, ballPosition.y + (int) playerShape.getHeight() / 3);
    }

    /**
     * called in PlayerTest
     * @return current ball position
     */
    public Point getBallPosition() {
        return ballPosition;
    }

    /**
     * called in PlayerTest
     * @return current move amount
     */
    public int getMoveAmount() {
        return moveAmount;
    }
}
