package BrickDestroyer.Model;

import java.awt.geom.Point2D;

public class BallFactory {
    /**
     * called in Wall
     */
    // use method makeBall to get object of type Ball
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
