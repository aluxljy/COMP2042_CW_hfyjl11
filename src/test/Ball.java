package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Created by filippo on 04/09/16.
 * Refactored by Looi Jie Ying on 03/12/21.
 */

abstract public class Ball {
    private Shape ballShape;

    private Point2D center;

    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    // Ball constructor
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border) {
        this.center = center;

        // instantiate Point2D object
        setUp(new Point2D.Double());
        setDown(new Point2D.Double());
        setLeft(new Point2D.Double());
        setRight(new Point2D.Double());

        // set the top, bottom, left and right location of the ball
        /*getUp().setLocation(center.getX(),center.getY() - (radiusB / 2));
        getDown().setLocation(center.getX(),center.getY() + (radiusB / 2));
        getLeft().setLocation(center.getX() - (radiusA / 2),center.getY());
        getRight().setLocation(center.getX() + (radiusA / 2),center.getY());*/

        ballShape = makeBallShape(center,radiusA,radiusB);
        this.border = border;
        this.inner = inner;
        speedX = 0;
        speedY = 0;
    }

    protected abstract Shape makeBallShape(Point2D center,int radiusA,int radiusB);

    /**
     * called in Wall
     */
    public void move() {
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));  // move center with axis-X and axis-Y speed

        /*RectangularShape tempBallShape = (RectangularShape) ballShape;  // type cast ballShape to RectangularShape
        double width = tempBallShape.getWidth();  // get width of rectangle
        double height = tempBallShape.getHeight();  // get height of rectangle

        tempBallShape.setFrame((center.getX() - (width / 2)),(center.getY() - (height / 2)),width,height);  // set the frame with center, width and height of tempBallShape
        setPoints(width,height);

        ballShape = tempBallShape;*/
        makeTempBallShape();
    }

    /**
     * called in Wall
     */
    public void setSpeed(int x,int y) {
        speedX = x;
        speedY = y;
    }

    /**
     * called in Wall
     */
    public void setSpeedX(int speed) {
        speedX = speed;
    }

    /**
     * called in Wall
     */
    public void setSpeedY(int speed) {
        speedY = speed;
    }

    /**
     * called in Wall
     */
    public void reverseX() {
        speedX *= -1;
    }

    /**
     * called in Wall
     */
    public void reverseY() {
        speedY *= -1;
    }

    /**
     * called in GameBoard
     */
    public Color getBorderColor() {
        return border;
    }

    /**
     * called in GameBoard
     */
    public Color getInnerColor() {
        return inner;
    }

    /**
     * called in Player & Wall
     */
    public Point2D getBallPosition() {
        return center;
    }

    /**
     * called in GameBoard
     */
    public Shape getBallShape() {
        return ballShape;
    }

    /**
     * similar code with move()
     * called in Wall
     */
    public void moveTo(Point position) {
        center.setLocation(position);

        /*RectangularShape tempBallShape = (RectangularShape) ballShape;
        double width = tempBallShape.getWidth();
        double height = tempBallShape.getHeight();

        tempBallShape.setFrame((center.getX() - (width / 2)),(center.getY() - (height / 2)),width,height);
        ballShape = tempBallShape;*/
        makeTempBallShape();
    }

    private void makeTempBallShape() {
        RectangularShape tempBallShape = (RectangularShape) ballShape;  // type cast ballShape to RectangularShape
        double width = tempBallShape.getWidth();  // get width of rectangle
        double height = tempBallShape.getHeight();  // get height of rectangle

        tempBallShape.setFrame((center.getX() - (width / 2)),(center.getY() - (height / 2)),width,height);  // set the frame with center, width and height of tempBallShape
        setPoints(width,height);

        ballShape = tempBallShape;
    }

    private void setPoints(double width,double height) {
        // set the top, bottom, left and right location of the ball
        getUp().setLocation(center.getX(),center.getY() - (height / 2));
        getDown().setLocation(center.getX(),center.getY() + (height / 2));
        getLeft().setLocation(center.getX() - (width / 2),center.getY());
        getRight().setLocation(center.getX() + (width / 2),center.getY());
    }

    /**
     * called in DebugConsole
     */
    public int getSpeedX() {
        return speedX;
    }

    /**
     * called in DebugConsole
     */
    public int getSpeedY() {
        return speedY;
    }

    /**
     * called in Brick & Wall
     */
    public Point2D getUp() {
        return up;
    }

    public void setUp(Point2D up) {
        this.up = up;
    }

    /**
     * called in Brick, Player & Wall
     */
    public Point2D getDown() {
        return down;
    }

    public void setDown(Point2D down) {
        this.down = down;
    }

    /**
     * called in Brick & Wall
     */
    public Point2D getLeft() {
        return left;
    }

    public void setLeft(Point2D left) {
        this.left = left;
    }

    /**
     * called in Brick & Wall
     */
    public Point2D getRight() {
        return right;
    }

    public void setRight(Point2D right) {
        this.right = right;
    }

}
