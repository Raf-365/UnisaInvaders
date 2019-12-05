/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fallingdown;

import fallingdown.Pepper;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author stefa
 */
public class PepperJPanel extends JPanel implements Runnable {
    
    private Pepper pepper;
    private final int DELAY = 10;
    private Thread animator;
    protected static final int B_WIDTH = 1920;
    protected static final int B_HEIGHT = 1080;
    private Obstacle[] imageArray = new Obstacle[7];


    
    
    
    
    public PepperJPanel() {
        initBoard();
    }

    private void initBoard() {
        
        addKeyListener(new TAdapter());
	setFocusable(true);

        pepper = new Pepper();
        pepper.addObserver(new SoundPlayerObserver("collide.wav","check.wav"));
        setBackground(Color.BLUE);
   
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        for (int i = 0; i < imageArray.length; i++)
            imageArray[i] = new Obstacle((i*70)*-1);
        
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
}

    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        doDrawing(g); 
        Toolkit.getDefaultToolkit().sync();
    }
    
    /*In the doDrawing() method, we draw Pepper with the drawImage() method. 
    We get the image and the coordinates from the sprite class. */
    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < imageArray.length; i++){
        imageArray[i].drawObstacle(g);
        Toolkit.getDefaultToolkit().sync();
        }
        g2d.drawImage(pepper.getImage(), pepper.getX(), 
            pepper.getY(), this);
    }
    
    
    /*We move the sprite and repaint the part of the board that has changed. 
    We use a small optimisation technique that repaints only the small area 
    of the window that actually changed. */
    private void step() {
        
        pepper.move();    
        for (int i = 0; i < imageArray.length; i++){
        imageArray[i].update();
        checkCollisions();
        }
        repaint();     
    }    

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            pepper.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            pepper.keyPressed(e);
        }
    }

    public void checkCollisions() {
      
        Rectangle r3 = pepper.getBounds();

        for (int i = 0; i < 7; i++) {
            Rectangle r2 = imageArray[i].getBounds();
           
            if (r3.intersects(r2)) {
                imageArray[i].setVisibles(false); 
                if(!imageArray[i].getCheckCollis()){                              
                pepper.setSt(1);
                imageArray[i].setCheckCollis(true);
                } 
            } else{
            imageArray[i].setCheckCollis(false);
            }

        }
    }
      
    
    
     @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            step();
            
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                
                JOptionPane.showMessageDialog(this, msg, "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
        }
    }
}