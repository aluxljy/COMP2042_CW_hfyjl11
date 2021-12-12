package BrickDestroyer.Model;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Created by filippo on 04/09/16.
 * Refactored by Looi Jie Ying on 03/12/21.
 */

/**
 * implements the basic features of the brick
 */
abstract public class Brick {
    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private String name;
    private Shape brickShape;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;

    /**
     * called in CementBrick, ClayBrick, SteelBrick, the Brick constructor takes in some parameters to make the shape of the brick and set the colour of the brick
     * @param name brick name
     * @param position position of the brick
     * @param size size of the brick
     * @param border colour of the border of the brick
     * @param inner inner colour of the brick
     * @param strength strength of the brick
     */
    public Brick(String name,Point position,Dimension size,Color border,Color inner,int strength) {
        broken = false;
        this.name = name;
        setBrickShape(makeBrickShape(position,size));
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;  // strength of brick
    }

    /**
     * changed from protected abstract method to private concrete method, to make the shape of the brick
     * @param position position of the brick
     * @param size size of the brick
     * @return rectangular shape of the brick
     */
    private Shape makeBrickShape(Point position,Dimension size) {
        return new Rectangle(position,size);
    }

    /**
     * called in Wall, to set the impact from a certain direction on a certain point of the brick
     * @param point point of impact on the brick
     * @param direction direction of impact on the brick
     * @return if the brick is broken or not
     */
    public boolean setImpact(Point2D point,int direction) {
        if(broken)
            return false;  // if broken then do not set impact
        impact();
        return broken;  // broken can be true or false
    }

    /**
     * called in GameBoard, to get the colour of the border of the brick
     * @return colour of the border of the brick
     */
    public Color getBorderColor() {
        return border;
    }

    /**
     * called in GameBoard, to get the inner colour of the brick
     * @return inner colour of the brick
     */
    public Color getInnerColor() {
        return inner;
    }

    /**
     * called in Wall, to find the direction of impact on the brick
     * @param ball the current ball
     * @return resultant impact the brick receives
     */
    public final int findImpact(Ball ball) {
        if(broken)
            return 0;  // if broken then no impact
        int output  = 0;
        if(getBrickShape().contains(ball.getRight()))
            output = LEFT_IMPACT;  // if the right side of the ball hits the brick, then brick receives left impact
        else if(getBrickShape().contains(ball.getLeft()))
            output = RIGHT_IMPACT;  // if the left side of the ball hits the brick, then brick receives right impact
        else if(getBrickShape().contains(ball.getUp()))
            output = DOWN_IMPACT;  // if the top side of the ball hits the brick, then brick receives down impact
        else if(getBrickShape().contains(ball.getDown()))
            output = UP_IMPACT;  // if the bottom side of the ball hits the brick, then brick receives up impact
        return output;
    }

    /**
     * called in CementBrick & GameBoard, getter
     * @return if the brick is broken or not
     */
    public boolean isBroken() {
        return broken;
    }

    /**
     * called in Wall, to repair the bricks
     */
    public void repair() {
        broken = false;
        strength = fullStrength;  // revert strength back to full strength
    }

    /**
     * called in SteelBrick & CementBrick, to apply impact on the brick and decrease its strength
     */
    public void impact() {
        strength--;  // decrease strength of brick
        broken = (strength == 0);  // if strength is 0 then broken is true
    }

    /**
     * called in CementBrick, Crack & GameBoard, getter
     * @return shape of the brick
     */
    public Shape getBrickShape() {
        return brickShape;
    }

    /**
     * setter
     * @param brickShape shape of the brick
     */
    public void setBrickShape(Shape brickShape) {
        this.brickShape = brickShape;
    }
}





