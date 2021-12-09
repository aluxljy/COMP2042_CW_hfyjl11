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

import java.awt.*;

public class Player {
    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerShape;
    private Point ballPosition;
    private int moveAmount;
    private int min;
    private int max;


    public Player(Point ballPosition,int width,int height,Rectangle container) {
        this.ballPosition = ballPosition;
        moveAmount = 0;
        playerShape = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    private Rectangle makeRectangle(int width,int height){
        Point point = new Point((int)(ballPosition.getX() - (width / 2)),(int) ballPosition.getY());
        return new Rectangle(point,new Dimension(width,height));
    }

    /**
     * called in Wall
     */
    public boolean impactWithBall(Ball ball){
        return playerShape.contains(ball.getBallPosition()) && playerShape.contains(ball.getDown()) ;
    }

    public void move(){
        double x = ballPosition.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPosition.setLocation(x, ballPosition.getY());
        playerShape.setLocation(ballPosition.x - (int) playerShape.getWidth() / 2, ballPosition.y);
    }

    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    public void moveRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    public void stop(){
        moveAmount = 0;
    }

    public Shape getPlayerShape(){
        return playerShape;
    }

    public void moveTo(Point position){
        ballPosition.setLocation(position);
        playerShape.setLocation(ballPosition.x - (int) playerShape.getWidth() / 2, ballPosition.y);
    }
}
