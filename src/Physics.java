/**
 * Created by dduren on 3/8/2016.
 */
public class Physics {
    private static double gravityFactor = 40;
    public static void addGravity(WorldObject object, double dt){
        double currentYVelocity = object.getVelocityY();
        double newYVelocity = currentYVelocity+(gravityFactor*dt);
        object.setVelocityY(newYVelocity);
    }
}
