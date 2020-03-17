
package dev.agpoon.dino.input;

import dev.agpoon.dino.Game;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener
{
    
    private Game game = new Game();
    
    private boolean left, right;
    
    int mx;
    int my;
    
    public MouseManager(){
        
    }
    
    public boolean isLeftPressed(){
        return left;
    }
    
    public boolean isRightPressed(){
        return right;
    }
    
    public int getMx(){
        return mx;
    }
    public int getMy(){
        return my;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
 
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        if(e.getButton() == MouseEvent.BUTTON1){
            left = true;
        }
        else if(e.getButton() == MouseEvent.BUTTON3){
            right = true;
        }
        
     /**private Rectangle play = new Rectangle((game.getWidth()/2)-50, 130, 100, 30);
        private Rectangle help = new Rectangle((game.getWidth()/2)-50, 180, 100, 30);
        private Rectangle quit = new Rectangle((game.getWidth()/2)-50, 230, 100, 30);*/

 
    }

    @Override
    public void mouseReleased(MouseEvent e) {
         
        if(e.getButton() == MouseEvent.BUTTON1){
            left = false;
        }
        else if(e.getButton() == MouseEvent.BUTTON3){
            right = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
 
    }

    @Override
    public void mouseDragged(MouseEvent e) {
 
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
    }
    
}
