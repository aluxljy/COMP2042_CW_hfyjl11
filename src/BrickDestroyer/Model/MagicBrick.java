package BrickDestroyer.Model;

import java.awt.*;
import java.util.Random;

/**
 * MagicBrick inherits the methods of Brick
 */
public class MagicBrick extends Brick {
    private static final String NAME = "Magic Brick";
    private static final Color DEF_INNER = new Color(51,153,255);
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int MAGIC_STRENGTH = 1;
    private static final double MAGIC_PROBABILITY = 0.3;

    private Random random;

    /**
     * called in BrickFactory, the MagicBrick constructor takes in parameters to make a magic brick
     * @param position position of the magic brick
     * @param size size of the magic brick
     */
    public MagicBrick(Point position, Dimension size) {
        super(NAME,position,size,DEF_BORDER,DEF_INNER,MAGIC_STRENGTH);
        random = new Random();
    }

    /**
     * overrides the method in Brick by implementing its own and randomize the increment and decrement of the score by 20 upon breaking
     */
    @Override
    public void impact() {
        super.setStrength(super.getStrength() - 1);  // decrease strength of brick
        super.setBroken((super.getStrength() == 0));  // if strength is 0 then broken is true

        if(super.isBroken()) {
            if(random.nextDouble() < MAGIC_PROBABILITY) {
                Wall.setTotalScore(Wall.getTotalScore() - 20);
            }
            else {
                Wall.setTotalScore(Wall.getTotalScore() + 20);
            }
        }
    }
}
