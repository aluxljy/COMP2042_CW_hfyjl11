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

import BrickDestroyer.Model.Brick;

import java.awt.*;
import java.util.Random;

public class SteelBrick extends Brick {
    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203,203,201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random random;
    //private Shape brickShape;

    /**
     * called in BrickFactory
     */
    // SteelBrick constructor
    public SteelBrick(Point position, Dimension size) {
        super(NAME,position,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        random = new Random();
        //brickShape = super.getBrickShape();
    }

    /*@Override
    protected Shape makeBrickShape(Point position,Dimension size) {
        return new Rectangle(position,size);
    }*/

   /* @Override
    public Shape getBrick() {
        return brickShape;
    }*/

    /*public boolean setImpact(Point2D point,int direction) {
        if(super.isBroken())
            return false;
        impact();
        return super.isBroken();
    }*/

    @Override
    public void impact() {
        if(random.nextDouble() < STEEL_PROBABILITY) {
            super.impact();  // if random is less than STEEL_PROBABILITY then call parent impact, else do nothing (steel brick does not break)
        }
    }
}