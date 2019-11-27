/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fallingdown;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;

/**
 *
 * @author Raffaele
 */
public class Obstaclessss extends JPanel implements Runnable {
    protected static final int B_WIDTH = 350;
    protected static final int B_HEIGHT = 350;
     private final int DEL = 3;
    private Obstacle[] imageArray = new Obstacle[5];
    private Thread anim;
  
    
    public Obstaclessss() {
        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        for (int i = 0; i < imageArray.length; i++)
            imageArray[i] = new Obstacle((i*50)*-1);
     
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        
        for (int i = 0; i < imageArray.length; i++){
        imageArray[i].drawObstacle(g);
        Toolkit.getDefaultToolkit().sync();
        }
        
    } 
    
   @Override
    public void addNotify() {
        super.addNotify();

        anim = new Thread(this);
        anim.start();
    }
        
        
    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        while (true) {

        for (int i = 0; i < imageArray.length; i++){
        imageArray[i].update();
        
        }
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DEL - timeDiff;//VERIFICO QUANTO CI METTONO CYCLE E REPAINT
            //PER AVERE UN AGGIORNAMENTO COSTANTE DELLE COORDINATE

            if (sleep < 0) {
                sleep = 2;
            }
            try{
                Thread.sleep(sleep);
            }catch(InterruptedException e){
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                    JOptionPane.showMessageDialog(this, msg, "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            beforeTime = System.currentTimeMillis();
        }
    
}
}
