package BrickDestroyer.Model;

import java.awt.*;
import java.awt.Point;

/**
 * Created by filippo on 04/09/16.
 * Refactored by Looi Jie Ying on 03/12/21.
 */

/**
 * ClayBrick inherits the methods of Brick
 */
public class ClayBrick extends Brick {
    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178,34,34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;

    /**
     * called in BrickFactory, the ClayBrick constructor takes in parameters to make a clay brick
     * @param position position of the clay brick
     * @param size size of the clay brick
     */
    public ClayBrick(Point position, Dimension size) {
        super(NAME,position,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }
}
