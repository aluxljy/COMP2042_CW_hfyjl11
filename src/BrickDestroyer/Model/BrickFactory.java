package BrickDestroyer.Model;

import java.awt.*;

/**
 * implemented factory pattern for Brick to use BrickFactory to create new objects
 */
public class BrickFactory {
    /**
     * called in Level, to get object of type Brick
     * @param brickType type of brick
     * @param point point of position of the brick
     * @param size size of the brick
     * @return new object of the brick type
     */
    public Brick makeBrick(String brickType, Point point, Dimension size) {
        if(brickType == null) {
            return null;
        }
        if(brickType.equalsIgnoreCase("CLAYBRICK")) {
            return new ClayBrick(point,size);
        }
        else if(brickType.equalsIgnoreCase("CEMENTBRICK")) {
            return new CementBrick(point,size);
        }
        else if(brickType.equalsIgnoreCase("STEELBRICK")) {
            return new SteelBrick(point,size);
        }
        return null;
    }
}
