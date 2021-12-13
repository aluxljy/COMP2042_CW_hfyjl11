package BrickDestroyer.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.geom.Point2D;

class PlayerTest {
    private Point2D center = new Point2D.Double(200, 200);
    private Point ballPosition = new Point(20, 20);

    Ball rubberBall = new RubberBall(center);
    Rectangle playerShape = new Rectangle(19,20,2,2);
    Player player = new Player(ballPosition, 2, 2, new Rectangle(0, 0, 200, 300));

    @Test
    void impact() {
        assertFalse(player.impactWithBall(rubberBall));
    }

    @Test
    void move() {
        player.move();
        assertEquals(center, rubberBall.getBallPosition());
    }

    @Test
    void moveLeft() {
        player.moveLeft();
        assertEquals(-5, player.getMoveAmount());
    }

    @Test
    void movRight() {
        player.moveRight();
        assertEquals(5, player.getMoveAmount());
    }

    @Test
    void stop() {
        player.stop();
        assertEquals(0, player.getMoveAmount());
    }

    @Test
    void getPlayerShape() {
        assertEquals(playerShape, player.getPlayerShape());
    }
}