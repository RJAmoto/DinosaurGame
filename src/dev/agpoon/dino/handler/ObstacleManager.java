
package dev.agpoon.dino.handler;

import dev.agpoon.dino.states.GameState;
import dev.agpoon.dino.worlds.Background;
import dev.agpoon.dino.worlds.Obstacle;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class ObstacleManager {
    
    Background ground;
    private boolean gameOver;
    
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

    public ObstacleManager() {
        obstacle = new ArrayList<Obstacle>();
        rectList = new ArrayList<Rectangle>();
        idList = new ArrayList<Integer>();
    }
    
    public void tick(){
        gameOver = GameState.getGameOver();
        if(!GameState.gameOver){
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
        else if(GameState.gameOver){
            
        }
    }
    
    public void render(Graphics g){
        if(!GameState.gameOver){
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
        else if(GameState.gameOver){
            
            for(int x = 0; x < obstacle.size(); x++){
                
                int newX = (int)obstacle.get(x).getX();
                int newY = (int)obstacle.get(x).getY();
                
                Obstacle ob = obstacle.get(x);
                if(ob.getId()<3){
                    
                    g.drawImage(ob.getImage(), newX, newY, null);
                    //ob.render(g);
                }
                else if(ob.getId()==3){
                    g.drawImage(ob.getCurrentImage(), newX, newY, null);
                    //ob.render2(g);
                }
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
    
    public void restart(){
            for(int x = 0; x < obstacle.size(); x++){
                obstacle.clear();
                rectList.clear();
                idList.clear();
        }
    }
}
