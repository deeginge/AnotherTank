import acm.graphics.GCanvas;
import acm.graphics.GPolygon;

/**
 * Created by dduren on 3/9/2016.
 */
public class Turret extends WorldObject{
    GPolygon turretRect = new GPolygon();
    private double originX;
    private double originY;
    private double degrees = 0;

    public Turret(double originX, double originY, double length, double height){
        this.originY = originY;
        this.originX = originX;
        turretRect = new GPolygon(originX, originY);
        this.object = turretRect;
        this.turretRect.setFilled(true);
        turretRect.addVertex(0, -(height/2));
        turretRect.addVertex(length, -(height/2));
        turretRect.addVertex(length, (height/2));
        turretRect.addVertex(0, (height/2));

    }

    public double[] getRotateOrigin() {
        double[] points = {this.originX, this.originY};
        return points;
    }
    public void rotate(double deg){
        this.degrees += deg;
        this.turretRect.rotate(deg);
    }
    public double getRotation(){
        return this.degrees;
    }
    /*
    public double getX(){
        return this.originX;
    }
    public double getY(){
        return this.originY;
    }*/
}
