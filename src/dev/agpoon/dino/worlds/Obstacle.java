
package dev.agpoon.dino.worlds;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Obstacle extends World{
    
    private Rectangle bounds;
    int rectWidth = 20, rectHeight = 30;
    
    private BufferedImage image;
    
    public Obstacle(BufferedImage image, int speed, int y){
        
        this.image = image;
        this.speed = speed;
        this.y = y;
        
        x = 500;
        bounds = new Rectangle(x+21, y+20, rectWidth, rectHeight);
        
    }

    public Obstacle() {

    }
    
    public Rectangle getObsBounds(){
        return bounds;
    }

    @Override
    public void tick() {
        bounds.setBounds(x+21, y+20, rectWidth, rectHeight);
        x-=speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
        //g.fillRect((int)bounds.getX(), (int)bounds.getY(), rectWidth, rectHeight);
    }
    
    @Override
    public float getX(){
        return x;
    }
    
    public void setX(float x){
        this.x = (int)x;
    }
    
    public void setSpeed(int speed){
        this.speed = speed;
    }
}
