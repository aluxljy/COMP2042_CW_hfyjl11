package test;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

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
     * called in CementBrick
     */
    // Crack constructor
    protected Crack(Brick brick,int crackDepth,int steps) {
        random = new Random();
        this.brick = brick;
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;
    }

    /**
     * called in CementBrick
     */
    protected GeneralPath draw() {
        return crack;
    }

    /**
     * called in CementBrick
     */
    protected void reset() {
        crack.reset();
    }

    /**
     * called in CementBrick
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

        /*switch(direction) {
            case LEFT:
                start.setLocation(bounds.x + bounds.width,bounds.y);
                end.setLocation(bounds.x + bounds.width,bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);

                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x,bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);

                break;
            case UP:
                start.setLocation(bounds.x,bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width,bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width,bounds.y);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);

                break;
        }*/
    }

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

    private int randomInBounds(int bound) {
        int number = (bound * 2) + 1;
        return getRandom().nextInt(number) - bound;
    }

    private boolean inMiddle(int i,int steps,int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return  (i > low) && (i < up);
    }

    private int jumps(int bound,double probability) {
        if(getRandom().nextDouble() > probability)
            return randomInBounds(bound);
        return 0;
    }

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
        /*switch(direction) {
            case HORIZONTAL:
                position = getRandom().nextInt(to.x - from.x) + from.x;
                point.setLocation(position,to.y);
                break;
            case VERTICAL:
                position = getRandom().nextInt(to.y - from.y) + from.y;
                point.setLocation(to.x,position);
                break;
        }*/
        return point;
    }

    public Random getRandom() {
        return random;
    }
}
