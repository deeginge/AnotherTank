/**
 * Created by dduren on 3/4/2016.
 */
public abstract class Projectile extends WorldObject {
    private int damage = 30;

    public void update(double dt){
        Physics.addGravity(this, dt);
        super.update(dt);
    }

    public int getDamage(){
        return damage;
    }
}
