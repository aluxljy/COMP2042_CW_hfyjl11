package test;

import java.awt.*;

public class Level {
    /*private Brick[][] levels;
    private int level;
    private int brickCount;*/

    public static Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCount, int lineCount, double brickSizeRatio, String type){
        BrickFactory brickFactory = new BrickFactory();
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
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

    }

    public static Brick[] makeChessboardLevel(Rectangle drawArea, int brickCount, int lineCount, double brickSizeRatio, String typeA, String typeB){
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
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCount)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLength;
            x =(line % 2 == 0) ? x : (x - (brickLength / 2));
            double y = (line) * brickHeight;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            //tmp[i] = b ?  Wall.makeBrick(p,brickSize,typeA) : Wall.makeBrick(p,brickSize,typeB);
            tmp[i] = b ? brickFactory.makeBrick(typeA,p,brickSize) : brickFactory.makeBrick(typeB,p,brickSize);
        }

        for(double y = brickHeight;i < tmp.length;i++, y += 2*brickHeight){
            double x = (brickOnLine * brickLength) - (brickLength / 2);
            p.setLocation(x,y);
            //tmp[i] = Wall.makeBrick(p,brickSize,typeA);
            tmp[i] = brickFactory.makeBrick(typeA,p,brickSize);
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
