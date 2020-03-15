
package dev.agpoon.dino.entities;

import java.awt.Graphics;

public abstract class Entity {
    
    public static Entity player;
    
    protected float x,y;
    
    public Entity(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    
}
