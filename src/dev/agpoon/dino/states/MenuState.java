
package dev.agpoon.dino.states;

import dev.agpoon.dino.Game;
import dev.agpoon.dino.gfx.Assets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class MenuState extends State{
    
    private BufferedImage background;
    
    private Color playColor;
    private Color helpColor;
    private Color quitColor;
    
    
    private Rectangle play = new Rectangle((game.getWidth()/2)-50, 130, 100, 30);
    private Rectangle help = new Rectangle((game.getWidth()/2)-50, 180, 100, 30);
    private Rectangle quit = new Rectangle((game.getWidth()/2)-50, 230, 100, 30);
    
    private String title = "Dinochrome";

    public MenuState(Game game) {
        super(game);
        
        playColor = Color.WHITE;
        helpColor = Color.WHITE;
        quitColor = Color.WHITE;
    }

    @Override
    public void tick() {
    //    System.out.println(game.getMouseManager().getMx()+"   "+game.getMouseManager().getMy());
        if(game.getMouseManager().getMx()>=200&&game.getMouseManager().getMx()<=300
                &&game.getMouseManager().getMy()>=130&&game.getMouseManager().getMy()<=160){
            
            playShiftBlue();
            
            if(game.getMouseManager().isLeftPressed()){
                game.setGame();
            }
        }
        else if(game.getMouseManager().getMx()>=200&&game.getMouseManager().getMx()<=300
           &&game.getMouseManager().getMy()>=180&&game.getMouseManager().getMy()<=210){
            
            helpShiftBlue();
            
            if(game.getMouseManager().isLeftPressed()){
                game.setHelp();
            }
        }
        else if(game.getMouseManager().getMx()>=200&&game.getMouseManager().getMx()<=300
           &&game.getMouseManager().getMy()>=230&&game.getMouseManager().getMy()<=260){
            
            quitShiftBlue();
            
            if(game.getMouseManager().isLeftPressed()){
                System.exit(0);
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
        g.setColor(Color.YELLOW);
        
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        
        g.drawString(title, 110,80);
        
        g.setFont(new Font("Alien Wars", Font.BOLD, 20));
        
        g.setColor(playColor);
        g2d.draw(play);
        g.drawString("PLAY", play.x+27,play.y+23);
        
        g.setColor(helpColor);
        g2d.draw(help);
        g.drawString("HELP", help.x+27,help.y+23);
        
        g.setColor(quitColor);
        g2d.draw(quit);
        g.drawString("QUIT", quit.x+27,quit.y+23);
    }
    
    public void playShiftBlue(){
        playColor = Color.GRAY;
    }
    public void helpShiftBlue(){
        helpColor = Color.GRAY;
    }
    public void quitShiftBlue(){
        quitColor = Color.GRAY;
    }
    public void colorShiftWhite(){
        playColor = Color.WHITE;
        helpColor = Color.WHITE;
        quitColor = Color.WHITE;
    }
    
    
}
