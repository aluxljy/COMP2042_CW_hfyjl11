package BrickDestroyer.Model;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * to make and draw the cracks on the cement brick
 */
public class Crack {
    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;

    private Brick brick;
    private Random random;

    private GeneralPath crack;

    private int crackDepth;
    private int steps;

    /**
     * called in CementBrick, the Crack constructor takes in some parameters to make the cracks
     * @param brick the current brick
     * @param crackDepth depth of the cracks
     * @param steps number of steps a crack contains
     */
    protected Crack(Brick brick,int crackDepth,int steps) {
        random = new Random();
        this.brick = brick;
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;
    }

    /**
     * called in CementBrick, to draw the crack path
     * @return path of the crack
     */
    protected GeneralPath draw() {
        return crack;
    }

    /**
     * called in CementBrick, to reset the cracks
     */
    protected void reset() {
        crack.reset();
    }

    /**
     * called in CementBrick
     * @param point point of the crack on the brick
     * @param direction direction of the crack on the brick
     */
    protected void makeCrack(Point2D point,int direction) {
        Rectangle bounds = brick.getBrickShape().getBounds();

        Point impact = new Point((int)point.getX(),(int)point.getY());
        Point start = new Point();
        Point end = new Point();

        if(direction == RIGHT) {
            start.setLocation(bounds.x + bounds.width,bounds.y);  // point of top right corner of brick
            end.setLocation(bounds.x + bounds.width,bounds.y + bounds.height);  // point of bottom right corner of brick
            Point tmp = makeRandomPoint(start,end,VERTICAL);  // right vertical line
            createCrack(impact,tmp);
        }
        else if(direction == LEFT) {
            start.setLocation(bounds.getLocation());  // point of top left corner of brick
            end.setLocation(bounds.x,bounds.y + bounds.height);  // point of bottom left corner of brick
            Point tmp = makeRandomPoint(start,end,VERTICAL);  // left vertical line
            createCrack(impact,tmp);
        }
        else if(direction == DOWN) {
            start.setLocation(bounds.x,bounds.y + bounds.height);  // point of bottom left corner of brick
            end.setLocation(bounds.x + bounds.width,bounds.y + bounds.height);  // point of bottom right corner of brick
            Point tmp = makeRandomPoint(start,end,HORIZONTAL);  // bottom horizontal line
            createCrack(impact,tmp);
        }
        else if(direction == UP) {
            start.setLocation(bounds.getLocation());  // point of top left corner of brick
            end.setLocation(bounds.x + bounds.width,bounds.y);  // point of top right corner of brick
            Point tmp = makeRandomPoint(start,end,HORIZONTAL);  // top horizontal line
            createCrack(impact,tmp);
        }
    }

    /**
     * to create the cracks with zigzag effects
     * @param start starting point of the crack
     * @param end end point of the crack
     */
    private void createCrack(Point start, Point end) {
        GeneralPath path = new GeneralPath();

        path.moveTo(start.x,start.y);

        double width = (end.x - start.x) / (double)steps;
        double height = (end.y - start.y) / (double)steps;

        int bound = crackDepth;
        int jump  = bound * 5;

        double x,y;

        for(int i = 1; i < steps;i++) {
            x = (i * width) + start.x;
            y = (i * height) + start.y + randomInBounds(bound);

            if(inMiddle(i,CRACK_SECTIONS,steps))
                y += jumps(jump,JUMP_PROBABILITY);

            path.lineTo(x,y);
        }
        path.lineTo(end.x,end.y);
        crack.append(path,true);
    }

    /**
     * to create random bounds
     * @param bound the bound of the number
     * @return random number
     */
    private int randomInBounds(int bound) {
        int number = (bound * 2) + 1;
        return getRandom().nextInt(number) - bound;
    }

    /**
     * to check if the number is in the middle
     * @param i number
     * @param steps steps taken
     * @param divisions step divisions
     * @return if the number is in between the range
     */
    private boolean inMiddle(int i,int steps,int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return  (i > low) && (i < up);
    }

    /**
     * @param bound the bound of the number
     * @param probability jump probability
     * @return random number in bounds
     */
    private int jumps(int bound,double probability) {
        if(getRandom().nextDouble() > probability)
            return randomInBounds(bound);
        return 0;
    }

    /**
     * to make a random point for the crack
     * @param from starting point
     * @param to end point
     * @param direction direction of the crack
     * @return random point of the crack
     */
    private Point makeRandomPoint(Point from,Point to,int direction) {
        Point point = new Point();
        int position;

        if(direction == HORIZONTAL) {
            position = getRandom().nextInt(to.x - from.x) + from.x;
            point.setLocation(position,to.y);  // vertical cracks
        }
        else if(direction == VERTICAL) {
            position = getRandom().nextInt(to.y - from.y) + from.y;
            point.setLocation(to.x,position);  // horizontal cracks
        }
        return point;
    }

    /**
     * getter
     * @return a random number
     */
    public Random getRandom() {
        return random;
    }
}
