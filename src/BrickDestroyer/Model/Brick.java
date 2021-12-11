package BrickDestroyer.Model;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Created by filippo on 04/09/16.
 * Refactored by Looi Jie Ying on 03/12/21.
 */

abstract public class Brick {
    //public static final int DEF_CRACK_DEPTH = 1;
    //public static final int DEF_STEPS = 35;

    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    /**
     * called in Crack
     */
    public Shape getBrickShape() {
        return brickShape;
    }

    public void setBrickShape(Shape brickShape) {
        this.brickShape = brickShape;
    }

/*
    public class Crack{
        private static final int CRACK_SECTIONS = 3;
        private static final double JUMP_PROBABILITY = 0.7;

        public static final int LEFT = 10;
        public static final int RIGHT = 20;
        public static final int UP = 30;
        public static final int DOWN = 40;
        public static final int VERTICAL = 100;
        public static final int HORIZONTAL = 200;

        private GeneralPath crack;

        private int crackDepth;
        private int steps;

        public Crack(int crackDepth, int steps){

            crack = new GeneralPath();
            this.crackDepth = crackDepth;
            this.steps = steps;
        }

        public GeneralPath draw(){
            return crack;
        }

        public void reset(){
            crack.reset();
        }

        protected void makeCrack(Point2D point, int direction){
            Rectangle bounds = Brick.this.getBrickShape().getBounds();

            Point impact = new Point((int)point.getX(),(int)point.getY());
            Point start = new Point();
            Point end = new Point();

            switch(direction){
                case LEFT:
                    start.setLocation(bounds.x + bounds.width, bounds.y);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    Point tmp = makeRandomPoint(start,end,VERTICAL);
                    makeCrack(impact,tmp);

                    break;
                case RIGHT:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start,end,VERTICAL);
                    makeCrack(impact,tmp);

                    break;
                case UP:
                    start.setLocation(bounds.x, bounds.y + bounds.height);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start,end,HORIZONTAL);
                    makeCrack(impact,tmp);
                    break;
                case DOWN:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x + bounds.width, bounds.y);
                    tmp = makeRandomPoint(start,end,HORIZONTAL);
                    makeCrack(impact,tmp);

                    break;
            }
        }

        protected void makeCrack(Point start, Point end){
            GeneralPath path = new GeneralPath();

            path.moveTo(start.x,start.y);

            double w = (end.x - start.x) / (double)steps;
            double h = (end.y - start.y) / (double)steps;

            int bound = crackDepth;
            int jump  = bound * 5;

            double x,y;

            for(int i = 1; i < steps;i++){

                x = (i * w) + start.x;
                y = (i * h) + start.y + randomInBounds(bound);

                if(inMiddle(i,CRACK_SECTIONS,steps))
                    y += jumps(jump,JUMP_PROBABILITY);

                path.lineTo(x,y);
            }

            path.lineTo(end.x,end.y);
            crack.append(path,true);
        }

        private int randomInBounds(int bound){
            int n = (bound * 2) + 1;
            return rnd.nextInt(n) - bound;
        }

        private boolean inMiddle(int i,int steps,int divisions){
            int low = (steps / divisions);
            int up = low * (divisions - 1);

            return  (i > low) && (i < up);
        }

        private int jumps(int bound,double probability){

            if(rnd.nextDouble() > probability)
                return randomInBounds(bound);
            return  0;
        }

        private Point makeRandomPoint(Point from,Point to, int direction){

            Point out = new Point();
            int pos;

            switch(direction){
                case HORIZONTAL:
                    pos = rnd.nextInt(to.x - from.x) + from.x;
                    out.setLocation(pos,to.y);
                    break;
                case VERTICAL:
                    pos = rnd.nextInt(to.y - from.y) + from.y;
                    out.setLocation(to.x,pos);
                    break;
            }
            return out;
        }
    }*/

    //private static Random random;

    private String name;
    private Shape brickShape;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;

    // Brick constructor
    public Brick(String name,Point position,Dimension size,Color border,Color inner,int strength) {
        //random = new Random();
        broken = false;
        this.name = name;
        setBrickShape(makeBrickShape(position,size));
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;  // strength of brick
    }

    //protected abstract Shape makeBrickShape(Point position,Dimension size);
    // changed from protected abstract method to private concrete method
    private Shape makeBrickShape(Point position,Dimension size) {
        return new Rectangle(position,size);
    }

    /**
     * called in Wall
     */
    public boolean setImpact(Point2D point,int direction) {
        if(broken)
            return false;  // if broken then do not set impact
        impact();
        return broken;  // broken can be true or false
    }

    //public abstract Shape getBrick();

    /**
     * called in GameBoard
     */
    public Color getBorderColor() {
        return border;
    }

    /**
     * called in GameBoard
     */
    public Color getInnerColor() {
        return inner;
    }

    /**
     * called in Wall
     */
    public final int findImpact(Ball ball) {
        if(broken)
            return 0;  // if broken then no impact
        int output  = 0;
        if(getBrickShape().contains(ball.getRight()))
            output = LEFT_IMPACT;  // if the right side of the ball hits the brick, then brick receives left impact
        else if(getBrickShape().contains(ball.getLeft()))
            output = RIGHT_IMPACT;  // if the left side of the ball hits the brick, then brick receives right impact
        else if(getBrickShape().contains(ball.getUp()))
            output = DOWN_IMPACT;  // if the top side of the ball hits the brick, then brick receives down impact
        else if(getBrickShape().contains(ball.getDown()))
            output = UP_IMPACT;  // if the bottom side of the ball hits the brick, then brick receives up impact
        return output;
    }

    /*public final boolean isBroken() {
        return broken;
    }*/
    /**
     * called in GameBoard
     */
    public boolean getBroken() {
        return broken;
    }

    /**
     * called in Wall
     */
    // repair the bricks
    public void repair() {
        broken = false;
        strength = fullStrength;  // revert strength back to full strength
    }

    public void impact() {
        strength--;  // decrease strength of brick
        broken = (strength == 0);  // if strength is 0 then broken is true
    }

    /*public static Random getRandom() {
        return random;
    }*/
}




