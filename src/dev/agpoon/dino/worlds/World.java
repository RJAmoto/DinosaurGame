package dev.agpoon.dino.worlds;

import java.awt.Graphics;

public abstract class World {
    
    protected int x, y;
    protected int speed;
    protected int defSpeed;
    private static Obstacle currentObs;
    
    public World(){
        defSpeed = speed;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    public static Obstacle getCurrentObstacle(){
        return currentObs;
    }
    public static void setObstacle(Obstacle obs){
        currentObs = obs;
    }
    public abstract float getX();
    
    public abstract float getY();
}