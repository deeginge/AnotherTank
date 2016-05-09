/**
 * Created by dduren on 3/4/2016.
 */
//OOP: Abstract Class
/*
This is an abstract class we use it setup for basic shot to inherit from
 */
public abstract class Projectile extends WorldObject {
    private int damage = 30;

    public void update(double dt){
        Physics.addGravity(this, dt);
        //OOP: Super Reference
        /*
        it is super reference from update
         */
        //System.out.println("test in Projectile update()");
        //OOP: Method Overriding
        /*
        we override a higher level of update with this one
         */
        super.update(dt);
    }

    public int getDamage(){
        return damage;
    }
}
