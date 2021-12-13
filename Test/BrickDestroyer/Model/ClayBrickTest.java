package BrickDestroyer.Model;

import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class ClayBrickTest {
    Point position = new Point(0, 0);
    Dimension size = new Dimension(40, 20);
    ClayBrick clayBrick = new ClayBrick(new Point(position), new Dimension(size));
    Rectangle expectedBrick = new Rectangle(new Point(position), new Dimension(size));

    @org.junit.jupiter.api.Test
    void getBrickShape() {
        assertEquals(expectedBrick, clayBrick.getBrickShape());
    }
}