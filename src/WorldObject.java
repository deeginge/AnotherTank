import acm.graphics.GCanvas;
import acm.graphics.GObject;

/**
 * Created by dduren on 3/3/2016.
 */
//OOP: Superclass
    /*
    World object is a super class because it extends into other classes
     */
public abstract class WorldObject implements Move{
    //OOP: Protected Modifier
    /*
    velocityX and velocityY are protected modifiers because they affect this class and aren't affect by others
     */
    protected double velocityX;
    protected double velocityY;
    protected GObject object;

    public void setSpeeds(double speedX, double speedY){
        this.velocityX = speedX;
        this.velocityY = speedY;
    }
    public void update(double deltaTime){
        this.object.move(deltaTime* velocityX, deltaTime* velocityY);
    }
    public void move(double deltaX, double deltaY){
        this.object.move(deltaX, deltaY);
        if(deltaX > 0 || deltaY>0){
            int x=2+2;
        }
    }
    public double getVelocityX(){
        return this.velocityX;
    }
    public double getVelocityY(){
        return this.velocityY;
    }
    public void setVelocityX(double speed){
        this.velocityX = speed;
    }
    public void setVelocityY(double speed){
        this.velocityY = speed;
    }
    public double getX(){
        return this.object.getX();
    }
    public double getY(){
        return this.object.getY();
    }
    public boolean intersects(double x, double y){
        boolean intersects = false;

        return intersects;
    }
    public boolean intersects(WorldObject object){
        return false;
    }
    public void addToCanvas(GCanvas canvas){

        canvas.add(this.object, this.getX(), this.getY());
    }
    @Override
    public String toString(){
        String result = "";
        result += object;
        return result;
    }
}
