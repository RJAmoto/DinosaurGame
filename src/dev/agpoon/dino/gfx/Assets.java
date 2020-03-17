
package dev.agpoon.dino.gfx;

import java.awt.image.BufferedImage;

public class Assets {
    public static final int size = 63;
    
    public static BufferedImage dinoRun[];
    public static BufferedImage dinoCrouch[];
    public static BufferedImage pterodactyl[];

    public static BufferedImage tree;
    public static BufferedImage cactus;
    
    public static BufferedImage ground;
    public static BufferedImage sky;
    public static BufferedImage mountain;
    
    public static BufferedImage menu;
    
    
    
    public static void init(){
        dinoRun = new BufferedImage[3];
        dinoCrouch = new BufferedImage[3];
        pterodactyl = new BufferedImage[5];
        
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        SpriteSheet bgSheet = new SpriteSheet(ImageLoader.loadImage("/textures/bgSheet.png"));
        menu = ImageLoader.loadImage("/textures/menuSheet.png");
         
        dinoRun[0] = sheet.crop(0, size+2, 63, 65);
        dinoRun[1] = sheet.crop(0,size*2+2, 63,65);
        dinoRun[2] = sheet.crop(0, size*3+3, 63, 63);
         
        dinoCrouch[0] = sheet.crop(size, size+2, 64, 65);
        dinoCrouch[1] = sheet.crop(size, size*2+2, 64, 65);
        dinoCrouch[2] = sheet.crop(size, size*3+4, 64, 63);
        
        pterodactyl[0] = sheet.crop(size*3, 0, size, size);
        pterodactyl[1] = sheet.crop(size*2, size, size, size);
        pterodactyl[2] = sheet.crop(size*3, size, size, size);
        pterodactyl[3] = sheet.crop(size*2, size*2, size, size);
        pterodactyl[4] = sheet.crop(size*3, size*2, size, size);
        
         
        tree = sheet.crop(size, 0, size, size);
        cactus = sheet.crop(size*2, 0, size, size);
         
        ground = bgSheet.crop(0, 381, 500*3, 155);
        sky = bgSheet.crop(0, 0, 500*3, 300);
        mountain = bgSheet.crop(0, 302, 500*3, 80);
    }
    
}
