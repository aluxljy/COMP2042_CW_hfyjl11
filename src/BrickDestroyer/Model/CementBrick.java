package BrickDestroyer.Model;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * CementBrick inherits the methods of Brick
 */
public class CementBrick extends Brick {
    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(147,147,147);
    private static final Color DEF_BORDER = new Color(217,199,175);
    private static final int CEMENT_STRENGTH = 2;

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;

    private Crack crack;
    private Shape brickShape;

    /**
     * called in BrickFactory, the CementBrick constructor takes in parameters to make a cement brick
     * @param position position of the cement brick
     * @param size size of the cement brick
     */
    public CementBrick(Point position,Dimension size) {
        super(NAME,position,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(this,DEF_CRACK_DEPTH,DEF_STEPS);
        brickShape = super.getBrickShape();
    }

    /**
     * called in Wall, overrides the method in Brick by implementing its own and increments the score again by 1 upon breaking
     * @param point point of impact on the brick
     * @param direction direction of impact on the brick
     * @return if the cement brick is broken or not
     */
    @Override
    public boolean setImpact(Point2D point,int direction) {
        if(super.isBroken())
            return false;  // if broken then do not set impact
        super.impact();

        if(super.isBroken()) {
            Wall.setTotalScore(Wall.getTotalScore() + 1);
        }
        else {
            crack.makeCrack(point,direction);  // if not broken then add crack on cement brick
            updateBrick();
            return false;
        }
        return true;
    }

    /**
     * called in Crack & GameBoard, getter
     * @return shape of the cement brick
     */
   @Override
    public Shape getBrickShape() {
        return this.brickShape;
    }

    /**
     * to update the shape of the cement brick
     */
    private void updateBrick() {
        if(!super.isBroken()) {
            GeneralPath output = crack.draw();  // draw the crack path
            output.append(super.getBrickShape(),false);
            brickShape = output;
        }
    }

    /**
     * called in Wall, overrides the method in Brick by implementing its own
     */
    @Override
    public void repair() {
        super.repair();
        crack.reset();  // remove crack path
        brickShape = super.getBrickShape();
    }
}
