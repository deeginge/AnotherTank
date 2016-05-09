import acm.graphics.GImage;
import acm.graphics.GRectangle;

/**
 * Created by dduren on 3/3/2016.
 */

// OOP: Subclass
    /*
    * World objects are anything that will be affected by gravity in the game.
    * To allow for easier expansion in the future, we made the Tank class
    * a WorldObject by extending the WorldObject class. This will allow us to
    * later include this in a list of objects to check for interactions with.
    * */
    //OOP: Polymorphism: Inheritance
    /*
    Tank inherits from world object and changes it
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
        //OOP: Overloaded Constructor
        /*
        turret we call here overloads the constructor because we change it in this class as we change the angle
         */
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
        //OOP: Single Array
        /*
        coordinates is a single array because are just doubles that hold the angles of the turrets.
         */
        coordinates[0] =  x;
        coordinates[1] = y;
        return coordinates;
    }
}