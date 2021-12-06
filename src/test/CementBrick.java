package test;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class CementBrick extends Brick {
    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(147,147,147);
    private static final Color DEF_BORDER = new Color(217,199,175);
    private static final int CEMENT_STRENGTH = 2;

    private Crack crack;
    private Shape brickShape;

    // CementBrick constructor
    public CementBrick(Point position,Dimension size) {
        super(NAME,position,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(this,DEF_CRACK_DEPTH,DEF_STEPS);
        brickShape = super.getBrickShape();
    }

    @Override
    protected Shape makeBrickShape(Point position,Dimension size) {
        return new Rectangle(position,size);
    }

    @Override
    public boolean setImpact(Point2D point,int direction) {
        if(super.isBroken())
            return false;  // if broken then do not set impact
        super.impact();
        if(!super.isBroken()) {
            crack.makeCrack(point,direction);  // if not broken then add crack on cement brick
            updateBrick();
            return false;
        }
        return true;
    }

    @Override
    public Shape getBrick() {
        return brickShape;
    }

    private void updateBrick() {
        if(!super.isBroken()) {
            GeneralPath output = crack.draw();  // draw the crack path
            output.append(super.getBrickShape(),false);
            brickShape = output;
        }
    }

    public void repair() {
        super.repair();
        crack.reset();  // remove crack path
        brickShape = super.getBrickShape();
    }
}
