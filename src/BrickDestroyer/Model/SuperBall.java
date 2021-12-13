package BrickDestroyer.Model;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * SuperBall inherits the methods of Ball
 */
public class SuperBall extends Ball {
    private static final int DEF_RADIUS = 6;
    private static final Color DEF_INNER_COLOR = new Color(255,0,110);
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();

    /**
     * called in BallFactory, the SuperBall constructor takes in a parameter to make a super ball
     * @param center center point of the super ball
     */
    public SuperBall(Point2D center) {
        super(center,DEF_RADIUS,DEF_RADIUS,DEF_INNER_COLOR,DEF_BORDER_COLOR);  // call Ball constructor
    }

    /**
     * overrides the abstract method in Ball by implementing its own
     * @param center center point of the ball
     * @param radiusA horizontal radius of the ball
     * @param radiusB vertical radius of the ball
     * @return
     */
    @Override
    protected Shape makeBallShape(Point2D center,int radiusA,int radiusB) {
        // framing the rectangle shape
        double x = center.getX() - (radiusA / 2);
        double y = center.getY() - (radiusB / 2);

        return new Ellipse2D.Double(x,y,radiusA,radiusB);
    }
}
