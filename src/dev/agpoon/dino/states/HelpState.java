package dev.agpoon.dino.states;

import dev.agpoon.dino.Game;
import dev.agpoon.dino.gfx.Assets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class HelpState extends State{
    
    private Color backColor;
    
    private Rectangle back = new Rectangle((game.getWidth()/2)-50, 250, 100, 30);

    public HelpState(Game game) {
        super(game);
    }

    @Override
    public void tick() {
        
        if(game.getMouseManager().getMx()>=200&&game.getMouseManager().getMx()<=300
                &&game.getMouseManager().getMy()>=250&&game.getMouseManager().getMy()<=280){
            
            backShiftBlue();
            
            if(game.getMouseManager().isLeftPressed()){
                try{
                    Thread.sleep(100);
                game.setMenu();
                }
                catch(InterruptedException e){
                    
                }
            }
        }
        else{
            colorShiftWhite();
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.menu, 0, 0, game.getWidth(), game.getHeight(), null);
        
        Graphics2D g2d = (Graphics2D) g;
       
        g.setFont(new Font("Alien Wars", Font.BOLD, 20));
        
        g.setColor(backColor);
        g2d.draw(back);
        
        g.drawString("BACK", back.x+27,back.y+23);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Nyala", Font.BOLD, 20));
        g.drawString("Use W, UP arrow key or space to JUMP", 80,60);
        g.drawString("use S, or Down arrow key to CROUCH", 80,100);
        g.drawString("The rule of the game: Just dont let the", 80,160);
        g.drawString("T-Rex hit an obstacle or any dinosaur", 80,190);
        
    }

    private void backShiftBlue() {
         backColor = Color.GRAY;
    }

    private void colorShiftWhite() {
        backColor = Color.WHITE;
    }
    
}
