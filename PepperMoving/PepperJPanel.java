/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperMoving;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
/**
 *
 * @author stefa
 */
public class PepperJPanel extends JPanel implements Runnable{
    
    private Pepper pepper;
    private final int DELAY = 10;
    private Timer timer;
    private Thread animator;
    
    public PepperJPanel() {
        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.white);
	setFocusable(true);

        pepper = new Pepper();
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
        g2d.drawImage(pepper.getImage(), pepper.getX(), 
            pepper.getY(), this);
    }
    
    
    /*We move the sprite and repaint the part of the board that has changed. 
    We use a small optimisation technique that repaints only the small area 
    of the window that actually changed. */
    private void step() {
        
        pepper.move();        
        repaint(pepper.getX()-1, pepper.getY()-1, 
                pepper.getWidth()+2, pepper.getHeight()+2);     
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
