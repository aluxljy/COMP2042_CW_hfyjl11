package test;

import java.awt.*;

public class BrickFactory {
    /**
     * called in Level
     */
    // use method makeBrick to get object of type Brick
    public Brick makeBrick(String brickType, Point point, Dimension size) {
        if(brickType == null) {
            return null;
        }
        if(brickType.equalsIgnoreCase("CLAYBRICK")) {
            return new ClayBrick(point,size);

        } else if(brickType.equalsIgnoreCase("CEMENTBRICK")) {
            return new CementBrick(point,size);

        } else if(brickType.equalsIgnoreCase("STEELBRICK")) {
            return new SteelBrick(point,size);
        }
        return null;
    }
}
