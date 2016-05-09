import acm.graphics.GDimension;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

/**
 * Created by dduren on 3/3/2016.
 */
public class Player extends java.lang.Object{
    int health = 100;
    String name = null;
    private static double turnTime = 45;
    Tank tank;
    double countdownTimerX = 585;
    double countdownTimerY = 20;
    public Player(String name, String tankImage){
        tank = new Tank(tankImage);
        this.name = name;
    }
    public void turn(){
        GLabel countdownTimer = new GLabel("Time Remaining:45", countdownTimerX, countdownTimerY);
        Game.canvas.add(countdownTimer);
        boolean oldFlag = Game.canvas.getAutoRepaintFlag();
        Game.canvas.setAutoRepaintFlag(true);
        long lastTime = System.nanoTime();
        long time;
        double dt;
        double timer = 0.0;
        System.out.println("loop");
        while (timer < turnTime) {
            time = System.nanoTime();
            dt = (time - lastTime) / 1000000000.0;
            lastTime = time;
            timer += dt;
            int timeRemaining = (int)(turnTime) - (int)(timer);
            String timerCountdown = Integer.toString(timeRemaining);
            countdownTimer.setLabel("Time Remaining:"+ timerCountdown);
            if(timeRemaining == 0) {
                Game.canvas.remove(countdownTimer);
            }
            {
                //write code here. Not much should be going on in this
                //method because it is simply allowing the player to move
                //their tank on the screen. Then when they fire a shot, the
                //fire button will take care of calling the player's tank
                //to actually fire a shot.
            }
        }
        Game.canvas.setAutoRepaintFlag(oldFlag);
    }
    public void lose(){
        this.tank.tankImage.setImage("\\images\\death.png");
        this.health = 0;
        Game.canvas.remove(this.tank.turret.object);
    }
    public void moveTank(double deltaX, double deltaY){
        tank.move(deltaX, deltaY);
    }
    public void fire(){
        double[] tipOfTurret = this.tank.getTipOfTurret();
        //System.out.println("X: " + tipOfTurret[0]);
        //System.out.println("Y: " + tipOfTurret[1]);
        BasicShot shot = new BasicShot(tipOfTurret[0], tipOfTurret[1]);
        GRectangle turretRect = this.tank.turret.turretRect.getBounds();
        double speedX = turretRect.getWidth();
        double speedY = -1*(turretRect.getHeight());
        shot.setSpeeds(speedX, speedY);

    }

    //takedamage passes in projectile and gets damage from projectile

    public int takeDamage(Projectile projectile ){
        health -= projectile.getDamage();
        return health;
    }
    

}
