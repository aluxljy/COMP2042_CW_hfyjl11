package BrickDestroyer.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.*;

class SteelBrickTest {
    Point position = new Point(0, 0);
    Dimension size = new Dimension(40, 20);
    SteelBrick steelBrick = new SteelBrick(new Point(position), new Dimension(size));
    Rectangle expectedBrick = new Rectangle(new Point(position), new Dimension(size));
    private static final double STEEL_PROBABILITY = 0.4;


    @Test
    void getBrickShape() {
        assertEquals(expectedBrick, steelBrick.getBrickShape());
    }

    @Test
    void setImpact() {
        steelBrick.setImpact(position, 20);
        if (steelBrick.isBroken())
            assertFalse(steelBrick.setImpact(position, 20));
    }

    @Test
    void impact() {
        steelBrick.impact();
        if (steelBrick.getRandom().nextDouble() < STEEL_PROBABILITY) {
            assertTrue(steelBrick.isBroken());
        }
        else {
            assertFalse(steelBrick.isBroken());
        }
    }
}