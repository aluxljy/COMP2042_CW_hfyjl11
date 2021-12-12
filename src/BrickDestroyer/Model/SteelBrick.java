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
import java.util.Random;

/**
 * SteelBrick inherits the methods of Brick
 */
public class SteelBrick extends Brick {
    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203,203,201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random random;

    /**
     * called in BrickFactory, the SteelBrick constructor takes in parameters to make a steel brick
     * @param position position of the steel brick
     * @param size size of the steel brick
     */
    public SteelBrick(Point position, Dimension size) {
        super(NAME,position,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        random = new Random();
    }

    /**
     * overrides the method in Brick by implementing its own and increments the score again by 3 upon breaking
     */
    @Override
    public void impact() {
        if(random.nextDouble() < STEEL_PROBABILITY) {
            super.impact();  // if random is less than STEEL_PROBABILITY then call parent impact, else do nothing (steel brick does not break)

            if(super.isBroken()) {
                Wall.setTotalScore(Wall.getTotalScore() + 3);
            }
        }
    }
}
