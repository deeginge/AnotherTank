/**
 * Created by dduren on 3/3/2016.
 */
public interface Move {
    void setSpeeds(double speedX, double speedY);
    void update(double deltaTime);
    double getVelocityX();
    double getVelocityY();

}
