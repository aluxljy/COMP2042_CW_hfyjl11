/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package BrickDestroyer.Model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * to make the wall using the bricks
 */
public class Wall {
    private static final int LEVELS_COUNT = 8;

    private static final String RUBBER = "RUBBERBALL";
    private static final String SUPER = "SUPERBALL";

    private static final String CLAY = "CLAYBRICK";
    private static final String STEEL = "STEELBRICK";
    private static final String CEMENT = "CEMENTBRICK";
    private static final String MAGIC = "MAGICBRICK";

    private Random random;
    private Rectangle area;

    private Brick[] bricks;
    private Ball ball;
    private Player player;

    private Brick[][] levels;
    private int level;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    public static int totalScore = 0;

    private BallFactory ballFactory;

    private Point ballPosition;

    /**
     * called in GameBoard, the Wall constructor takes in some parameters to make the wall for the game
     * @param drawArea area of the rectangular shape
     * @param brickDimensionRatio ratio of brick dimension
     * @param ballPosition position of the ball
     */
    public Wall(Rectangle drawArea,double brickDimensionRatio, Point ballPosition) {
        this.startPoint = new Point(ballPosition);
        this.ballPosition = ballPosition;

        levels = makeLevels(drawArea,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        random = new Random();

        ballFactory = new BallFactory();
        setBall(ballFactory.makeBall(RUBBER,ballPosition));

        randomSpeed(5,4,4,2);

        setPlayer(new Player((Point) ballPosition.clone(),100,10, drawArea));

        area = drawArea;
    }

    /**
     * to make different levels with different brick combination
     * @param drawArea area of the rectangular shape
     * @param brickDimensionRatio ratio of brick dimension
     * @return a multidimensional array
     */
    private Brick[][] makeLevels(Rectangle drawArea,double brickDimensionRatio) {
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = Level.makeCombinationLevel(drawArea,30,3,brickDimensionRatio,CLAY,CLAY,CLAY);
        tmp[1] = Level.makeCombinationLevel(drawArea,30,3,brickDimensionRatio,CLAY,CEMENT,CEMENT);
        tmp[2] = Level.makeCombinationLevel(drawArea,30,3,brickDimensionRatio,CLAY,STEEL,STEEL);
        tmp[3] = Level.makeCombinationLevel(drawArea,30,3,brickDimensionRatio,CEMENT,CEMENT,MAGIC);
        tmp[4] = Level.makeCombinationLevel(drawArea,40,4,brickDimensionRatio,CLAY,CLAY,MAGIC);
        tmp[5] = Level.makeCombinationLevel(drawArea,40,4,brickDimensionRatio,CLAY,CEMENT,STEEL);
        tmp[6] = Level.makeCombinationLevel(drawArea,40,4,brickDimensionRatio,STEEL,CEMENT,MAGIC);
        tmp[7] = Level.makeCombinationLevel(drawArea,50,5,brickDimensionRatio,MAGIC,CEMENT,STEEL);
        return tmp;
    }

    /**
     * called in GameBoard, to make the player and ball move
     */
    public void move(){
        getPlayer().move();
        getBall().move();
    }

    /**
     * called in GameBoard, to find the impacts occurred
     */
    public void findImpacts() {
        if(getPlayer().impactWithBall(getBall())) {
            getBall().reverseY();
        }
        else if(impactWithWall()) {
            /*for efficiency reverse is done into method impactWall
            * because for every brick program checks for horizontal and vertical impacts
            */
            brickCount--;
        }
        else if(impactWithBorder()) {
            getBall().reverseX();
        }
        else if(getBall().getBallPosition().getY() < area.getY()) {
            getBall().reverseY();
        }
        else if(getBall().getBallPosition().getY() > area.getY() + area.getHeight()) {
            ballCount--;
            ballLost = true;
        }
    }

    /**
     * to find the impact between the ball and the bricks
     * @return if the direction of the impact is true or false
     */
    private boolean impactWithWall(){
        for(Brick brick : getBricks()){
            if(brick.findImpact(getBall()) == Brick.UP_IMPACT) {
                //Vertical impact
                getBall().reverseY();  // if the bottom side of the ball hits the top side of the brick, then ball rebounds upwards
                return brick.setImpact(getBall().getDown(), Crack.DOWN);
            }
            else if(brick.findImpact(getBall()) == Brick.DOWN_IMPACT) {
                //Vertical impact
                getBall().reverseY();  // if the top side of the ball hits the bottom side of the brick, then ball rebounds downwards
                return brick.setImpact(getBall().getUp(),Crack.UP);
            }
            else if(brick.findImpact(getBall()) == Brick.LEFT_IMPACT) {
                //Horizontal impact
                getBall().reverseX();  // if the right side of the ball hits the left side of the brick, then ball rebounds to the left
                return brick.setImpact(getBall().getRight(),Crack.RIGHT);
            }
            else if(brick.findImpact(getBall()) == Brick.RIGHT_IMPACT) {
                //Horizontal impact
                getBall().reverseX();  // if the left side of the ball hits the right side of the brick, then ball rebounds to the right
                return brick.setImpact(getBall().getLeft(),Crack.LEFT);
            }
        }
        return false;
    }

    /**
     * to find the impact between the ball and the border
     * @return if the impact between the ball and the border are true or false
     */
    private boolean impactWithBorder(){
        Point2D point = getBall().getBallPosition();
        return ((point.getX() < area.getX()) || (point.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * called in GameBoard, getter
     * @return number of bricks
     */
    public int getBrickCount(){
        return brickCount;
    }

    /**
     * called in GameBoard, getter
     * @return number of balls
     */
    public int getBallCount(){
        return ballCount;
    }

    /**
     * called in GameBoard, getter
     * @return if the ball is lost
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * called in GameBoardController & GameBoard, to reset the ball back to the original position
     */
    public void ballReset(){
        getPlayer().moveTo(startPoint);
        getBall().moveTo(startPoint);

        randomSpeed(5,4,4,2);
        ballLost = false;
    }

    /**
     * to produce random speed
     * @param xMax maximum of x speed
     * @param xMin minimum of x speed
     * @param yMax maximum of y speed
     * @param yMin minimum of y speed
     */
    public void randomSpeed(int xMax, int xMin, int yMax, int yMin) {
        int speedX,speedY;
        do{
            speedX = random.nextInt(xMax - xMin + 1) - xMin;
        }
        while(speedX == 0);
        do{
            speedY = -random.nextInt(yMax - yMin + 1) - yMin;
        }
        while(speedY == 0);

        getBall().setSpeed(speedX,speedY);
    }

    /**
     * called in GameBoardController & GameBoard, to reset the walls by repairing them
     */
    public void wallReset(){
        for(Brick brick : getBricks())
            brick.repair();
        brickCount = getBricks().length;
        ballCount = 3;
    }

    /**
     * called in GameBoard, to check if all the balls are used up
     * @return true if all the balls are used up
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * called in GameBoard, to check if all the bricks are destroyed
     * @return true id all the bricks are destroyed
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * called in DebugPanel & GameBoard, to move to the next level
     */
    public void nextLevel(){
        setBricks(levels[level++]);
        this.brickCount = getBricks().length;

        if(level > 4) {
            setBall(ballFactory.makeBall(SUPER,ballPosition));
            randomSpeed(7,6,7,5);
        }
    }

    /**
     * called in GameBoard, to check if there are anymore levels
     * @return true if there are more levels left
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * called in DebugPanel, to set the x speed of the ball
     * @param speed speed of x
     */
    public void setBallXSpeed(int speed){
        getBall().setSpeedX(speed);
    }

    /**
     * called in DebugPanel, to set the y speed of the ball
     * @param speed speed of y
     */
    public void setBallYSpeed(int speed){
        getBall().setSpeedY(speed);
    }

    /**
     * called in DebugPanel, to reset the number of balls
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * called in GameBoard, getter
     * @return array of bricks
     */
    public Brick[] getBricks() {
        return bricks;
    }

    /**
     * setter
     * @param bricks array of bricks
     */
    public void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    /**
     * called in DebugConsoleController & GameBoard, getter
     * @return current ball
     */
    public Ball getBall() {
        return ball;
    }

    /**
     * setter
     * @param ball current ball
     */
    public void setBall(Ball ball) {
        this.ball = ball;
    }

    /**
     * called in GameBoardController & GameBoard
     * @return current player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * setter
     * @param player current player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * called in Brick, CementBrick, MagicBrick, SteelBrick & GameBoard, getter
     * @return current total score
     */
    public static int getTotalScore() {
        return totalScore;
    }

    /**
     * called in Brick, CementBrick, MagicBrick, SteelBrick & GameBoard, setter
     * @param totalScore current total score
     */
    public static void setTotalScore(int totalScore) {
        Wall.totalScore = totalScore;
    }
}
