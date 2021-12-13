package BrickDestroyer.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;

class RubberBallTest {
    private final int DEF_RADIUS = 12;
    private final Color DEF_INNER_COLOR = new Color(255, 219, 88);
    private final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();
    private Point2D center = new Point2D.Double(300, 400);
    Point position = new Point(0, 0);

    RubberBall rubberBall = new RubberBall(center);

    @Test
    void move() {
        Shape rubberBallShape = rubberBall.makeBallShape(center, DEF_RADIUS, DEF_RADIUS);
        RectangularShape ball = (RectangularShape) rubberBallShape;
        assertEquals(ball, rubberBallShape);
    }

    @Test
    void setSpeed() {
        rubberBall.setSpeed(3, 3);
        assertEquals(3, rubberBall.getSpeedX());
        assertEquals(3, rubberBall.getSpeedY());
    }

    @Test
    void setSpeedX() {
        rubberBall.setSpeedX(12);
        assertEquals(12, rubberBall.getSpeedX());
    }

    @Test
    void setSpeedY() {
        rubberBall.setSpeedY(18);
        assertEquals(18, rubberBall.getSpeedY());
    }

    @Test
    void reverseX() {
        rubberBall.setSpeedX(10);
        rubberBall.reverseX();
        assertEquals(-10, rubberBall.getSpeedX());
    }

    @Test
    void reverseY() {
        rubberBall.setSpeedY(50);
        rubberBall.reverseY();
        assertEquals(-50, rubberBall.getSpeedY());
    }

    @Test
    void getBorderColor() {
        assertEquals(DEF_BORDER_COLOR, rubberBall.getBorderColor());
    }

    @Test
    void getInnerColor() {
        assertEquals(DEF_BORDER_COLOR, rubberBall.getBorderColor());
    }

    @Test
    void getBallPosition() {
        assertEquals(center, rubberBall.getBallPosition());
    }

    @Test
    void getBallShape() {
        Shape rubberBallShape = rubberBall.makeBallShape(center, DEF_RADIUS, DEF_RADIUS);
        assertEquals(rubberBallShape, rubberBall.getBallShape());
    }

    @Test
    void moveTo() {
        Shape rubberBallShape = rubberBall.makeBallShape(center, DEF_RADIUS, DEF_RADIUS);
        RectangularShape ball = (RectangularShape) rubberBallShape;
        assertEquals(ball, rubberBallShape);
        rubberBall.moveTo(position);
        assertEquals(position, rubberBall.getBallPosition());
    }

    @Test
    void getSpeedX() {
        rubberBall.setSpeedX(300);
        assertEquals(300, rubberBall.getSpeedX());
    }

    @Test
    void getSpeedY() {
        rubberBall.setSpeedY(400);
        assertEquals(400, rubberBall.getSpeedY());
    }

    @Test
    void makeBallShape() {
        double x = center.getX() - (DEF_RADIUS/2);
        double y = center.getY() - (DEF_RADIUS/2);
        Ellipse2D expectedBall = new Ellipse2D.Double(x, y, DEF_RADIUS, DEF_RADIUS);
        assertEquals(expectedBall, rubberBall.makeBallShape(center, DEF_RADIUS, DEF_RADIUS));
    }
}