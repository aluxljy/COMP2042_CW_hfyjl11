package BrickDestroyer.Model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Created by filippo on 04/09/16.
 * Refactored by Looi Jie Ying on 03/12/21.
 */

/**
 * implements the basic features of the ball
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

    /**
     * called in RubberBall, the Ball constructor takes in some parameters to make the shape of the ball and set the colour of the ball
     * @param center center point of the ball
     * @param radiusA horizontal radius of the ball
     * @param radiusB vertical radius of the ball
     * @param inner inner colour of the ball
     * @param border colour of the border of the ball
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border) {
        this.center = center;

        // instantiate Point2D object
        setUp(new Point2D.Double());
        setDown(new Point2D.Double());
        setLeft(new Point2D.Double());
        setRight(new Point2D.Double());

        ballShape = makeBallShape(center,radiusA,radiusB);
        this.border = border;
        this.inner = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * abstract method to allow customization of the ball
     * @param center center point of the ball
     * @param radiusA horizontal radius of the ball
     * @param radiusB vertical radius of the ball
     * @return shape of the ball
     */
    protected abstract Shape makeBallShape(Point2D center,int radiusA,int radiusB);

    /**
     * called in Wall, to set the location of the center of the ball when it is moving
     */
    public void move() {
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));  // move center with axis-X and axis-Y speed
        makeTempBallShape();
    }

    /**
     * called in Wall, to set the horizontal and vertical speed of the ball
     * @param x horizontal speed of the ball
     * @param y vertical speed of the ball
     */
    public void setSpeed(int x,int y) {
        speedX = x;
        speedY = y;
    }

    /**
     * called in Wall, to set the horizontal speed of the ball
     * @param speed horizontal speed of the ball
     */
    public void setSpeedX(int speed) {
        speedX = speed;
    }

    /**
     * called in Wall, to set the vertical speed of the ball
     * @param speed vertical speed of the ball
     */
    public void setSpeedY(int speed) {
        speedY = speed;
    }

    /**
     * called in Wall, to reverse the horizontal speed of the ball
     */
    public void reverseX() {
        speedX *= -1;
    }

    /**
     * called in Wall, to reverse the vertical speed of the ball
     */
    public void reverseY() {
        speedY *= -1;
    }

    /**
     * called in GameBoard, to get the colour of the border of the ball
     * @return colour of the border of the ball
     */
    public Color getBorderColor() {
        return border;
    }

    /**
     * called in GameBoard, to get the inner colour of the ball
     * @return inner colour of the ball
     */
    public Color getInnerColor() {
        return inner;
    }

    /**
     * called in Player & Wall, to get the position of the ball
     * @return center point of the ball
     */
    public Point2D getBallPosition() {
        return center;
    }

    /**
     * called in GameBoard, to get the shape of the ball
     * @return shape of the ball
     */
    public Shape getBallShape() {
        return ballShape;
    }

    /**
     * called in Wall, to set the location of the center of the ball when it is moved to a certain position
     * @param position desired position to move the ball to
     */
    public void moveTo(Point position) {
        center.setLocation(position);
        makeTempBallShape();
    }

    /**
     * to make a temporary rectangular shape for the ball
     */
    private void makeTempBallShape() {
        RectangularShape tempBallShape = (RectangularShape) ballShape;  // type cast ballShape to RectangularShape
        double width = tempBallShape.getWidth();  // get width of rectangle
        double height = tempBallShape.getHeight();  // get height of rectangle

        tempBallShape.setFrame((center.getX() - (width / 2)),(center.getY() - (height / 2)),width,height);  // set the frame with center, width and height of tempBallShape
        setPoints(width,height);

        ballShape = tempBallShape;
    }

    /**
     * to set the points of temporary rectangular shape for the ball
     * @param width width of the ball
     * @param height height of the ball
     */
    private void setPoints(double width,double height) {
        // set the top, bottom, left and right location of the ball
        getUp().setLocation(center.getX(),center.getY() - (height / 2));
        getDown().setLocation(center.getX(),center.getY() + (height / 2));
        getLeft().setLocation(center.getX() - (width / 2),center.getY());
        getRight().setLocation(center.getX() + (width / 2),center.getY());
    }

    /**
     * called in DebugConsoleController, getter
     * @return horizontal speed of the ball
     */
    public int getSpeedX() {
        return speedX;
    }

    /**
     * called in DebugConsoleController, getter
     * @return vertical speed of the ball
     */
    public int getSpeedY() {
        return speedY;
    }

    /**
     * called in Brick & Wall, getter
     * @return top horizontal location of the ball
     */
    public Point2D getUp() {
        return up;
    }

    /**
     * setter
     * @param up top horizontal location of the ball
     */
    public void setUp(Point2D up) {
        this.up = up;
    }

    /**
     * called in Brick, Player & Wall, getter
     * @return bottom horizontal location of the ball
     */
    public Point2D getDown() {
        return down;
    }

    /**
     * setter
     * @param down bottom horizontal location of the ball
     */
    public void setDown(Point2D down) {
        this.down = down;
    }

    /**
     * called in Brick & Wall, getter
     * @return left vertical location of the ball
     */
    public Point2D getLeft() {
        return left;
    }

    /**
     * setter
     * @param left left vertical location of the ball
     */
    public void setLeft(Point2D left) {
        this.left = left;
    }

    /**
     * called in Brick & Wall, getter
     * return right vertical location of the ball
     */
    public Point2D getRight() {
        return right;
    }

    /**
     * setter
     * @param right vertical right location of the ball
     */
    public void setRight(Point2D right) {
        this.right = right;
    }

}
