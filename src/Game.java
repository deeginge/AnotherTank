/**
 * Created by dduren on 3/3/2016.
 */
import acm.graphics.GCanvas;
/**
 * Created by dduren on 3/3/2016.
 */
import acm.graphics.GImage;
import acm.graphics.GRectangle;
import acm.program.GraphicsProgram;
import acm.graphics.GLabel;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game extends GraphicsProgram{

    private Player p1;
    private Player p2;
    private Player currentPlayer; //this is the object the move, angle, and fire buttons will call when clicked.

    private final int WORLD_WIDTH = 1500;
    private final int WORLD_HEIGHT = 800;
    public static GCanvas canvas;
    private GLabel winner;
    private double p1HealthX = 20;
    private double p1HealthY = 20;
    private GLabel p1Health = new GLabel("100", p1HealthX, p1HealthY);
    private double p2HealthX = 1150;
    private double p2HealthY = 20;
    private GLabel  p2Health = new GLabel("100", p2HealthX, p2HealthY);
    private GLabel gameMessages = new GLabel("Sample Message", WORLD_WIDTH/2, 20);
    private GImage terrain;
    private String gameTitle = "Tank Battles";
    private int terrainDepth = 10;
    private int terrainSquareSize = 5;
    private int tankMoveSpeed = 2;
    private double tankMargin = 20;
    private static ArrayList<WorldObject> worldObjects = new ArrayList<WorldObject>(5);
    private static ArrayList<Projectile> projectiles = new ArrayList<Projectile>(5);

    public void run(){
        prepareGame();
        startGame();
    }


    private void prepareGame(){
        canvas = this.getGCanvas();
        canvas.setBackground(Color.cyan);
        this.setSize(WORLD_WIDTH, WORLD_HEIGHT);
        createPlayers();
        String player1Health = Integer.toString(p1.health);
        p1Health = new GLabel("Player 1 Health:" + player1Health, p1HealthX, p1HealthY);
        canvas.add(p1Health);
        String player2Health = Integer.toString(p2.health);
        p2Health = new GLabel("Player 2 Health:" + player2Health, p2HealthX, p2HealthY);
        canvas.add(p2Health);
        setTitle(gameTitle);
        addTerrain();
        spawnTanks();
        addKeyListeners();


    }

    private void startGame(){

        playLoop();
    }
    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public void playLoop(){
            canvas.setAutoRepaintFlag(false);

            long lastTime = System.nanoTime();
            long time;
            double dt;
            double fpsTimer = 0.0;
            double timer = 0.0;
            long counter = 0;
            long lastCounter = 0;

            while (true) {

                ++counter;
                time = System.nanoTime();
                dt = (time - lastTime) / 1000000000.0;

                playList(dt);
                canvas.repaint();

                lastTime = time;
                timer += dt;
                fpsTimer += dt;

                if (fpsTimer >= 1) {
                    fpsTimer -= 1;
                    long fps = counter - lastCounter;
                    lastCounter = counter;
                }
                if(declareWinner(p1, p2)){
                    return;
                }
            }
        }
    private void playList(double dt){
        for(int k = 0; k< worldObjects.size(); k++){
            if(worldObjects.get(k) != null) {
                worldObjects.get(k).update(dt);
            }
            checkCollisions(p1);
            checkCollisions(p2);
        }
    }

    public void keyPressed(KeyEvent event){
        char key = event.getKeyChar();
        int code = event.getKeyCode();
        System.out.println(key);
        if(key == 'w'){
            currentPlayer.tank.turret.rotate(-1);
        }
        else if(key == 'a'){
            currentPlayer.tank.setSpeeds(-20, 0);
        }
        else if(key == 's'){
            currentPlayer.tank.turret.rotate(1);
        }
        else if(key == 'd'){
            currentPlayer.tank.setSpeeds(20, 0);
        }
        else if(key == 'b'){
            currentPlayer.lose();
        }
        else if(KeyEvent.VK_LEFT == code){
            currentPlayer.tank.setSpeeds(-20, 0);
        }
        else if(KeyEvent.VK_RIGHT == code){
            currentPlayer.tank.setSpeeds(20, 0);
        }
        else if(KeyEvent.VK_UP == code){
            currentPlayer.tank.turret.rotate(-1);
        }
        else if(KeyEvent.VK_DOWN == code){
            currentPlayer.tank.turret.rotate(1);
        }
        else if(KeyEvent.VK_SPACE == code){
            currentPlayer.fire();
        }
        else{
            //System.out.println("Key = " + key);
            //System.out.println("Code = " + code);
        }

    }

    public void keyReleased(KeyEvent event){
        char key = event.getKeyChar();
        int code = event.getKeyCode();
        System.out.println(key);
        if(key == 'w'){
            currentPlayer.tank.turret.rotate(0);
        }
        else if(key == 'a'){
            currentPlayer.tank.setSpeeds(0,0);
        }
        else if(key == 's'){
            currentPlayer.tank.turret.rotate(0);
        }
        else if(key == 'd'){
            currentPlayer.tank.setSpeeds(0,0);
        }
        else if(KeyEvent.VK_LEFT == code){
            currentPlayer.tank.setSpeeds(0,0);
        }
        else if(KeyEvent.VK_RIGHT == code){
            currentPlayer.tank.setSpeeds(0,0);
        }
        else if(KeyEvent.VK_UP == code){
            currentPlayer.tank.turret.rotate(0);
        }
        else if(KeyEvent.VK_DOWN == code){
            currentPlayer.tank.turret.rotate(0);
            //System.out.println("Turret Rotation: " + currentPlayer.tank.turret.getRotation());
        }
    }
    /*public void addTerrain(){
        double lastYBound = canvas.getHeight()-terrainSquareSize*terrainDepth;
        double lastXBound = 0;
        GRect groundRectangle;
        for(int column = 0; column<terrainDepth; column++){
            for(int row = 0; row<canvas.getWidth(); row++) {
                groundRectangle = new GRect(terrainSquareSize, terrainSquareSize);
                groundRectangle.setFilled(true);
                groundRectangle.setFillColor(Color.magenta);
                groundRectangle.sendToBack();
                canvas.add(groundRectangle, lastXBound, lastYBound);
                lastXBound += terrainSquareSize;
            }
            lastXBound = 0;
            lastYBound += terrainSquareSize;
        }
    }*/
    public void addTerrain(){
        terrain = new GImage("\\images\\terrain.png");
        double canvas_height = canvas.getHeight();
        double image_height = terrain.getHeight();

        canvas.add(terrain, 0, canvas_height-image_height);
    }
    private boolean declareWinner(Player p1, Player p2){
        boolean result = false;
        if(p1.health <= 0){
            winner = new GLabel("Player 2 wins!", 585, 500);
            canvas.add(winner);
            result = true;
        }
        else if(p2.health <= 0){
            winner = new GLabel("Player 1 wins!", 585, 500);
            canvas.add(winner);
            result = true;
        }
        //check health levels and declare a winner on screen. :D
        return result;
    }
    private void createPlayers(){
        p1 = new Player("Player 1", "\\images\\redtank.png");
        p2 = new Player("Player 2", "\\images\\bluetank.png");
        currentPlayer = p1;
    }
    private void spawnTanks(){//Players should be refactored to an array for optimal results.

        double p1TankHeight = p1.tank.tankImage.getHeight();
        double p1TankWidth = p1.tank.tankImage.getWidth();
        p1.moveTank(tankMargin, terrain.getY()-(p1TankHeight/2));
        p1.tank.tankImage.sendToFront();
        double p2TankHeight = p2.tank.tankImage.getHeight();
        double p2TankWidth = p2.tank.tankImage.getWidth();
        p2.moveTank(canvas.getWidth()-p2TankWidth-tankMargin, terrain.getY()-(p2TankHeight/2));
        p2.tank.tankImage.sendToFront();
    }
    private void checkCollisions(Player player) {
        System.out.println("checking for colisions" + projectiles.size());
        for (int j = 0; j < projectiles.size(); j++) {
            System.out.println("inside loop");
            GRectangle projectile = projectiles.get(j).object.getBounds();
            GRectangle tank = player.tank.tankImage.getBounds();
            if(projectile.getX()>tank.getX()){
                if(projectile.getY()>tank.getY()) {
                    if(projectile.getY()<tank.getY()+tank.getHeight()){
                        if(projectile.getX()<tank.getX()+ tank.getWidth()){
                            player.lose();

                        }
                    }
                }
            }
            /*System.out.println("X" + projectile.getX());
            System.out.println("Y" + projectile.getY());
            if(projectile.getX()>tank.getX()){
                player.lose();
            }
            if(projectile.getY()>tank.getY()){
                player.lose();
            }*/
        }
    }
    public static void addToProjectiles(Projectile projectile){
        projectiles.add(projectile);
    }
    public static void addToWorldObjects(WorldObject object){
        worldObjects.add(object);
    }}