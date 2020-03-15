
package dev.agpoon.dino;

import dev.agpoon.dino.display.Display;
import dev.agpoon.dino.gfx.Assets;
import dev.agpoon.dino.input.KeyManager;
import dev.agpoon.dino.states.GameState;
import dev.agpoon.dino.states.State;
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
    
    //Constructor
    public Game(String title, int width, int height){
        
        this.title = title;
        this.width = width;
        this.height = height;
        
         keymanager = new KeyManager();

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
        
        State gameState = new GameState(this);
        State.setState(gameState);
      
    }
    
    public KeyManager getKeyManager(){
        return keymanager;
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
}
