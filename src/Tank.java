import acm.graphics.GImage;
import acm.graphics.GRectangle;

/**
 * Created by dduren on 3/3/2016.
 */

public class Tank extends WorldObject {
    GImage tankImage =  new GImage("images\\bluetank.png");
    Turret turret;
    double turretOriginX;
    double turretOriginY;

    double turretLength = 30;
    double turretHeight = 3;

    public Tank(String imageFile){
        tankImage = new GImage(imageFile);
        object = tankImage;
        turretOriginX = this.getX()+tankImage.getWidth()/2;
        turretOriginY = this.getY()+tankImage.getHeight()/2;
        turret = new Turret(turretOriginX, turretOriginY, turretLength, turretHeight);

        this.addToCanvas(Game.canvas);
        turret.addToCanvas(Game.canvas);
        Game.addToWorldObjects(this);
    }


    public void update(double dt){
       move(this.getVelocityX() * dt, dt*this.getVelocityY());
    }
    public void move(double deltaX, double deltaY){
        this.object.move(deltaX, deltaY);
        //System.out.println("Tank X: " + this.getX());
        //System.out.println("Tank moved by: " + deltaX);
        turret.move(deltaX, deltaY);
        //System.out.println("Turret X: " + turret.getX());
        //System.out.println("Turret moved by: " + deltaX);
    }
    public double[] getTipOfTurret(){
        double[] coordinates = new double[2];
        double x;
        double y;
        GRectangle turretDimensions = this.turret.turretRect.getBounds();

        if(this.turret.getRotation() <= 90) {
            x = turretDimensions.getX()+turretDimensions.getWidth();
            y = turretDimensions.getY();
        }
        else{
            x = turretDimensions.getX();
            y = turretDimensions.getY();
        }
        coordinates[0] =  x;
        coordinates[1] = y;
        return coordinates;
    }
}