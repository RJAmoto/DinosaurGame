
package dev.agpoon.dino.handler;

import dev.agpoon.dino.worlds.Background;
import dev.agpoon.dino.worlds.Obstacle;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class ObstacleManager {
    
    Background ground;
    
    private ArrayList<Obstacle> obstacle;
    Rectangle obsRect;
    private ArrayList<Rectangle> rectList;
    private ArrayList<Integer> idList;
    
    public ObstacleManager(Background ground){
        
        this.ground = ground;
        obstacle = new ArrayList<Obstacle>();
        rectList = new ArrayList<Rectangle>();
        idList = new ArrayList<Integer>();
        
        obsRect = new Rectangle();
        
    }
    
    public void tick(){
        
        for(int x = 0; x < obstacle.size(); x++){

            Obstacle ob = obstacle.get(x);
            obstacle.get(x).setSpeed(ground.getSpeed());
            Rectangle rect = (ob.getObsBounds());
            
            
            if(ob.getX()<=-64){
                obstacle.remove(ob);
                rectList.remove(rect);
                idList.remove(idList.get(0));
            }
            ob.tick();
        }
        
    }
    
    public void render(Graphics g){
        for(int x = 0; x < obstacle.size(); x++){
            Obstacle ob = obstacle.get(x);
            if(ob.getId()<3){
                ob.render(g);
            }
            else if(ob.getId()==3){
                ob.render2(g);
            }
        }
    }
    
    public void addObstacle(Obstacle obs){
        obstacle.add(obs);
        rectList.add(obs.getObsBounds());
        idList.add(obs.getId());
    }
 
    public Obstacle getLastObstacle(){
        return obstacle.get(obstacle.size()-1);
    }
    
    public ArrayList getObsList(){
        return obstacle;
    }
    
    public void setObsRect(Rectangle rect){
        obsRect = rect;
    }
    
    public Rectangle getObsRect(){
        return obsRect;
    }
    
    public ArrayList getRectList(){
        return rectList;
    }
}
