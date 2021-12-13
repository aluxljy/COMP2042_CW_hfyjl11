package BrickDestroyer.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.geom.Point2D;

class CrackTest {
    private Point2D center = new Point2D.Double(400, 200);
    Point position = new Point(0, 0);
    Dimension size = new Dimension(40, 20);
    Brick brick = new CementBrick(position, size);
    Crack crack = new Crack(brick, 3, 4);

    @Test
    void draw() {
        assertNotNull(crack.draw());
    }

    @Test
    void reset() {
        crack.reset();
        assertNotNull(crack);
    }

    @Test
    void makeCrack() {
        crack.makeCrack(center, 10);
        assertNotNull(crack);
    }
}