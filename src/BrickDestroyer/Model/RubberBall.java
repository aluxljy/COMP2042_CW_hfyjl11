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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * RubberBall inherits the methods of Ball
 */
public class RubberBall extends Ball {
    private static final int DEF_RADIUS = 12;
    private static final Color DEF_INNER_COLOR = new Color(255,219,88);
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();

    /**
     * called in BallFactory, the RubberBall constructor takes in a parameter to make a rubber ball
     * @param center center point of the rubber ball
     */
    public RubberBall(Point2D center) {
        super(center,DEF_RADIUS,DEF_RADIUS,DEF_INNER_COLOR,DEF_BORDER_COLOR);  // call Ball constructor
    }

    /**
     * overrides the abstract method in Ball by implementing its own
     * @param center center point of the ball
     * @param radiusA horizontal radius of the ball
     * @param radiusB vertical radius of the ball
     * @return ellipse shape of the rubber ball
     */
    @Override
    protected Shape makeBallShape(Point2D center,int radiusA,int radiusB) {
        // framing the rectangle shape
        double x = center.getX() - (radiusA / 2);
        double y = center.getY() - (radiusB / 2);

        return new Ellipse2D.Double(x,y,radiusA,radiusB);
    }
}
