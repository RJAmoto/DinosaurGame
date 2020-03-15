
package dev.agpoon.dino.display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;


public class Display {
    
    private String title;
    private int width, height;
    private JFrame frame;
    private Canvas canvas;
    
    public Display(String title, int width, int height){
        
        this.title = title;
        this.width = width;
        this.height = height;
        
        createDisplay();
    }
    public void createDisplay(){
        
        frame = new JFrame(title);
        canvas = new Canvas();
        
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        canvas.setPreferredSize(new Dimension( width, height));
        canvas.setMinimumSize(new Dimension( width, height));
        canvas.setMaximumSize(new Dimension( width, height));
        canvas.setFocusable(false);
        
        frame.add(canvas);
        frame.pack();
    }
    public Canvas getCanvas(){
        return canvas;
    }
    public JFrame getFrame(){
        return frame;
    }
}