/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.agpoon.dino.worlds;

import dev.agpoon.dino.ui.Scoring;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Background extends World{
    private BufferedImage image;
    private int yRes;
    
    public Background(BufferedImage image, int speed, int y){
        this.y = y;
        yRes = y;
        x = 0;
        this.image = image;
        this.speed = speed;
    }

    @Override
    public void tick() {
        x-=speed;
        if(x<=-1000){
            x=0;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
    }
    
    public void addSpeed(float speed){
        this.speed+=speed;
    }
    
    public int getSpeed(){
        return speed;
    }

    @Override
    public float getX(){
        return x;
    }
    public void restart(){
        this.x = 0;
        this.y = yRes;
    }

    @Override
    public float getY() {
        return y;
    }
}
