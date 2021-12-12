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
     * called in GameBoard
     */
    // Wall constructor
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
     * called in GameBoard
     */
    public void move(){
        getPlayer().move();
        getBall().move();
    }

    /**
     * called in GameBoard
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

    private boolean impactWithBorder(){
        Point2D point = getBall().getBallPosition();
        return ((point.getX() < area.getX()) || (point.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * called in GameBoard
     */
    public int getBrickCount(){
        return brickCount;
    }

    /**
     * called in GameBoard
     */
    public int getBallCount(){
        return ballCount;
    }

    /**
     * called in GameBoard
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * called in GameBoard
     */
    public void ballReset(){
        getPlayer().moveTo(startPoint);
        getBall().moveTo(startPoint);

        randomSpeed(5,4,4,2);
        ballLost = false;
    }

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
     * called in GameBoard
     */
    public void wallReset(){
        for(Brick brick : getBricks())
            brick.repair();
        brickCount = getBricks().length;
        ballCount = 3;
    }

    /**
     * called in GameBoard
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * called in GameBoard
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * called in DebugPanel & GameBoard
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
     * called in GameBoard
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * called in DebugPanel
     */
    public void setBallXSpeed(int speed){
        getBall().setSpeedX(speed);
    }

    /**
     * called in DebugPanel
     */
    public void setBallYSpeed(int speed){
        getBall().setSpeedY(speed);
    }

    /**
     * called in DebugPanel
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * called in GameBoard
     */
    public Brick[] getBricks() {
        return bricks;
    }

    public void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    /**
     * called in DebugConsole & GameBoard
     */
    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    /**
     * called in GameBoard
     */
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static int getTotalScore() {
        return totalScore;
    }

    public static void setTotalScore(int totalScore) {
        Wall.totalScore = totalScore;
    }
}
