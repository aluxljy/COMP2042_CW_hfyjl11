package BrickDestroyer.Model;

import java.awt.geom.Point2D;

/**
 * implemented factory pattern for Ball to use BallFactory to create new objects
 */
public class BallFactory {
    /**
     * called in Wall, to get object of type Ball
     * @param ballType type of ball
     * @param center center point of the ball
     * @return new object of the ball type
     */
    public Ball makeBall(String ballType,Point2D center) {
        if(ballType == null) {
            return null;
        }
        if(ballType.equalsIgnoreCase("RUBBERBALL")) {
            return new RubberBall(center);
        }
        return null;
    }
}
