package BrickDestroyer.Model;

import BrickDestroyer.Model.Brick;
import BrickDestroyer.Model.BrickFactory;

import java.awt.*;

public class Level {
    /*private Brick[][] levels;
    private int level;
    private int brickCount;*/

    /*public static Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCount, int lineCount, double brickSizeRatio, String type){
        BrickFactory brickFactory = new BrickFactory();
        *//*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         *//*
        brickCount -= brickCount % lineCount;

        int brickOnLine = brickCount / lineCount;

        double brickLength = drawArea.getWidth() / brickOnLine;
        double brickHeight = brickLength / brickSizeRatio;

        brickCount += lineCount / 2;

        Brick[] tmp  = new Brick[brickCount];

        Dimension brickSize = new Dimension((int) brickLength,(int) brickHeight);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCount)
                break;
            double x = (i % brickOnLine) * brickLength;
            x =(line % 2 == 0) ? x : (x - (brickLength / 2));
            double y = (line) * brickHeight;
            p.setLocation(x,y);
            //tmp[i] = Wall.makeBrick(p,brickSize,type);
            tmp[i] = brickFactory.makeBrick(type,p,brickSize);
        }

        for(double y = brickHeight;i < tmp.length;i++, y += 2*brickHeight){
            double x = (brickOnLine * brickLength) - (brickLength / 2);
            p.setLocation(x,y);
            //tmp[i] = Wall.makeBrick(p,brickSize,type);
            tmp[i] = brickFactory.makeBrick(type,p,brickSize);
        }
        return tmp;

    }*/

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
            //tmp[i] = brick ?  Wall.makeBrick(point,brickSize,typeA) : Wall.makeBrick(point,brickSize,typeB);
            tmp[i] = brick ? brickFactory.makeBrick(typeA,point,brickSize) : brickFactory.makeBrick(typeB,point,brickSize);
        }

        for(double y = brickHeight;i < tmp.length;i++, y += 2*brickHeight){
            double x = (brickOnLine * brickLength) - (brickLength / 2);
            point.setLocation(x,y);
            //tmp[i] = Wall.makeBrick(point,brickSize,typeA);
            tmp[i] = brickFactory.makeBrick(typeA,point,brickSize);
        }
        return tmp;
    }
   /* public void nextLevel(){
        Wall.setBricks(levels[level++]);
        this.brickCount = Wall.getBricks().length;
    }

    public boolean hasLevel(){
        return level < levels.length;
    }*/
}
