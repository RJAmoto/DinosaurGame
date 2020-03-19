
package dev.agpoon.dino;

import dev.agpoon.dino.display.Display;
import dev.agpoon.dino.gfx.Assets;
import dev.agpoon.dino.handler.ObstacleManager;
import dev.agpoon.dino.input.KeyManager;
import dev.agpoon.dino.input.MouseManager;
import dev.agpoon.dino.states.GameState;
import dev.agpoon.dino.states.HelpState;
import dev.agpoon.dino.states.MenuState;
import dev.agpoon.dino.states.State;
import dev.agpoon.dino.ui.Scoring;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
    
    Thread t;
    private boolean running;
    private Display display;
    
    private BufferStrategy bs;
    Graphics g;
    String title;
    int width;
    int height;
    
    private KeyManager keymanager;
    private MouseManager mousemanager;
    
    State gameState ;
    State menuState;
    State helpState;
    
    //Constructor
    public Game(String title, int width, int height){
        
        this.title = title;
        this.width = width;
        this.height = height;
        
         keymanager = new KeyManager();
         mousemanager = new MouseManager();

    }
    
    public Game(){
        
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    @Override
    public void run() {
        init();
        
        int fps = 60;
        double TimePerTick = 1000000000/fps;
        double delta = 0;
        long now;
        long LastTime = System.nanoTime();
        
        while(running){
            
            now = System.nanoTime();
            delta += (now - LastTime)/TimePerTick;
            LastTime = now;
            
            if(delta>=1){
                tick();
                render();
                delta--;
            }
        }
        stop();
        
    }
    
    //initializes everything
    public void init(){
        Assets.init();
        display = new Display(title, width, height);
        
        display.getFrame().addKeyListener(keymanager);
        display.getFrame().addMouseListener(mousemanager);
        display.getFrame().addMouseMotionListener(mousemanager);
        display.getCanvas().addMouseListener(mousemanager);
        display.getCanvas().addMouseMotionListener(mousemanager);
        
        gameState = new GameState(this);
        menuState = new MenuState(this);
        helpState = new HelpState(this);
        
        State.setState(menuState);
      
    }
    
    public KeyManager getKeyManager(){
        return keymanager;
    }
    public MouseManager getMouseManager(){
        return mousemanager;
    }
    
    public void tick(){
        keymanager.tick();
        
        if(State.getCurrentState()!=null){
            State.getCurrentState().tick();
        }
    }
    public void render(){
         bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        
        //clear
        g.clearRect(0, 0, width, height);
        //draw
        
            if(State.getCurrentState()!=null){
                State.getCurrentState().render(g);
            }
        
        //end
        bs.show();
        g.dispose();
    }
    
    public synchronized void start(){
        if(running){
            return;
        }
        else{
            running = true;
            t = new Thread(this);
            t.start();
        }
    }
    
    public void setGame(){
        State.setState(gameState);
        GameState.gameOver = false;
        Scoring.restart();
    }
    
    public synchronized void stop(){
        if (!running){
            return;
        }
        else{
            running = false;
            try {
                t.join();
            } catch (InterruptedException ex) {
               System.exit(1);
            }
        }
    }   

    public void setMenu() {
        State.setState(menuState);
    }

    public void setHelp() {
        State.setState(helpState);
    }
}
