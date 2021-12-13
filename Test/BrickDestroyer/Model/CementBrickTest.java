package BrickDestroyer.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.*;

class CementBrickTest {
    Point position = new Point(0, 0);
    Dimension size = new Dimension(40, 20);
    CementBrick cementBrick = new CementBrick(new Point(position), new Dimension(size));
    Rectangle expectedBrick = new Rectangle(new Point(position), new Dimension(size));

    @Test
    void setImpact() {
        assertEquals(cementBrick.isBroken(), cementBrick.setImpact(position, 20));
    }

    @Test
    void getBrickShape() {
        assertEquals(expectedBrick, cementBrick.getBrickShape());
    }

    @Test
    void repair() {
        cementBrick.repair();
        assertFalse(cementBrick.isBroken());
    }
}