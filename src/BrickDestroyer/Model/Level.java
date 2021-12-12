package BrickDestroyer.Model;

import java.awt.*;

/**
 * to arrange different bricks together into different combinations that can be used to make new levels
 */
public class Level {
    /**
     * called in Wall, to arrange 2 types of bricks into a chessboard pattern
     * @param drawArea area of the rectangular shape
     * @param brickCount number of bricks
     * @param lineCount number of lines
     * @param brickSizeRatio ratio of the brick size
     * @param typeA name of brickA
     * @param typeB name of brickB
     * @return a list of bricks
     */
    public static Brick[] makeChessboardLevel(Rectangle drawArea, int brickCount, int lineCount, double brickSizeRatio, String typeA, String typeB) {
        BrickFactory brickFactory = new BrickFactory();
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCount -= brickCount % lineCount;

        int brickOnLine = brickCount / lineCount;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLength = drawArea.getWidth() / brickOnLine;
        double brickHeight = brickLength / brickSizeRatio;

        brickCount += lineCount / 2;

        Brick[] tmp  = new Brick[brickCount];

        Dimension brickSize = new Dimension((int) brickLength,(int) brickHeight);
        Point point = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCount)
                break;
            int positionX = i % brickOnLine;
            double x = positionX * brickLength;
            x =(line % 2 == 0) ? x : (x - (brickLength / 2));
            double y = (line) * brickHeight;
            point.setLocation(x,y);

            boolean brick = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && positionX > centerLeft && positionX <= centerRight));
            tmp[i] = brick ? brickFactory.makeBrick(typeA,point,brickSize) : brickFactory.makeBrick(typeB,point,brickSize);
        }

        for(double y = brickHeight;i < tmp.length;i++, y += 2*brickHeight){
            double x = (brickOnLine * brickLength) - (brickLength / 2);
            point.setLocation(x,y);
            tmp[i] = brickFactory.makeBrick(typeA,point,brickSize);
        }
        return tmp;
    }
}
