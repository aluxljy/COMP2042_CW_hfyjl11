package test;

import java.awt.*;
import java.awt.Point;

/**
 * Created by filippo on 04/09/16.
 * Refactored by Looi Jie Ying on 03/12/21.
 */

public class ClayBrick extends Brick {
    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178,34,34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;

    // ClayBrick constructor
    public ClayBrick(Point position, Dimension size) {
        super(NAME,position,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    @Override
    protected Shape makeBrickShape(Point position,Dimension size) {
        return new Rectangle(position,size);
    }

    @Override
    public Shape getBrick() {
        return super.getBrickShape();
    }
}
