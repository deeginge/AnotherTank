import acm.graphics.GOval;

import java.awt.*;

/**
 * Created by dduren on 3/4/2016.
 */
public class BasicShot extends Projectile {

    private GOval basicShot;
    private int dimensions = 3;
    private double initialYVelocity = 500;

    public BasicShot(double xLocation, double yLocation){
        basicShot = new GOval(xLocation, yLocation, dimensions, dimensions);
        basicShot.setFillColor(Color.pink);
        basicShot.setFilled(true);
        this.object = basicShot;
        Game.addToWorldObjects(this);
        Game.addToProjectiles(this);
        Game.canvas.add(basicShot, xLocation, yLocation);

    }


}
