/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

import entities.*;
import java.awt.Image;


/**
 *
 * @author stefa
 */
public class MainView extends JPanel{
    private Pepper pepper;
    private final int DELAY = 10, MALUS=-10, BONUS=1;
    private Book[] booksArray;
    private static boolean ingame;
    private JProgressBar healthBar;
    private Font font1, font2;
    private JLabel healthLabel, punteggioLabel, bonusLabel, malusLabel;
    private float punteggio;
    private int punteggioBonus, punteggioMalus;
    private MainController mainController;
    private Image imagePepper, imageBook;
    
    public static boolean getIngame(){
        return ingame;
    }

    public MainView() {
        initBoard();
    }

    private void initBoard() {
        mainController = MainController.getController(); //SINGLETON
        //addKeyListener(new TAdapter());
        //setFocusable(true);
        ingame = true;
        //pepper = new Pepper();
        punteggio = 0;
        punteggioBonus=0;
        punteggioMalus=0;
        pepper=mainController.getPepperController().getPepper();
        graphicsSetup();       
        //pepper.addObserver(new SoundPlayerObserver("collide.wav", "check.wav","shot.wav","splat.wav"));
        //pepper.addObserver(new UpdateHealthBarObserver(healthBar));
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(GameFrame.MAX_X, GameFrame.MAX_Y));
        //for (int i = 0; i < imageArray.length; i++) {
        //    imageArray[i] = new Obstacle((i * 70) * -1);
        //}
    }
    

    private void graphicsSetup(){
        this.setLayout(null);
        font1 = new Font("Helvetica", Font.BOLD, 20);
        font2 = new Font("Helvetica", Font.BOLD, 30);
        
        punteggioLabel = new JLabel("Punteggio: "+punteggio);
        punteggioLabel.setFont(font1);
        punteggioLabel.setForeground(Color.yellow);
        punteggioLabel.setBounds(5, 90, 200, 30);
        
        bonusLabel = new JLabel("Bonus: "+punteggioBonus);
        bonusLabel.setFont(font1);
        bonusLabel.setForeground(Color.yellow);
        bonusLabel.setBounds(5, 130, 200, 30);
        
        malusLabel = new JLabel("Malus: "+punteggioMalus);
        malusLabel.setFont(font1);
        malusLabel.setForeground(Color.yellow);
        malusLabel.setBounds(5, 170, 200, 30);
        
        healthLabel = new JLabel("HEALTH BAR");
        healthLabel.setFont(font1);
        healthLabel.setForeground(Color.white);
        healthLabel.setBounds(5, 5, 200, 30);
        
        healthBar=new JProgressBar(0, 5);
        healthBar.setStringPainted(true);
        healthBar.setForeground(Color.RED);
        healthBar.setString(Integer.toString(pepper.getHealth()));        
        healthBar.setBounds(5, 35, 130, 40);
        healthBar.setValue(pepper.getHealth());
        healthBar.setFont(font2);
        
        add(healthLabel);
        add(healthBar);
        add(punteggioLabel);
        add(malusLabel);
        add(bonusLabel);
    }
    
    public void repaintBooks(Book[] booksArray ){
        this.booksArray=booksArray;
        repaint();
        
    }
    
    /*
    private void drawMissiles(Graphics g) {

        ArrayList<Missile> missileArray = pepper.getMissiles();
        for (int i = 0; i < missileArray.size(); i++) {
            Missile m = missileArray.get(i);
            if (m.isVisible()) {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
        }
    }*/

    @Override
    public void paintComponent(Graphics g) {
        
        if (ingame) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
        
            for (int i = 0; i < booksArray.length; i++) {
                Book book = booksArray[i];
                drawBook(g, book);            
                Toolkit.getDefaultToolkit().sync();
            }
            
            g2d.drawImage(pepper.getImage(), pepper.getX(),
                pepper.getY(), this);
            
            //doDrawing(g);
            //drawMissiles(g);
            
        } else {
            drawGameOver(g);
        }
    }    
    
    private void drawGameOver(Graphics g){       

        String msg = "Game over, you're dead, for today is enough, you can stop here!";
        FontMetrics fm = getFontMetrics(font2);

        g.setColor(Color.white);
        g.setFont(font2);
        g.drawString(msg, (GameFrame.MAX_X - fm.stringWidth(msg)) / 2,
                GameFrame.MAX_Y / 2);    
        
    }

    /*In the doDrawing() method, we draw Pepper with the drawImage() method. 
    We get the image and the coordinates from the sprite class. */
    /*private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        
        for (int i = 0; i < imageArray.length; i++) {
            
            drawBooks(g);
            
            Toolkit.getDefaultToolkit().sync();
        }
        //g2d.drawImage(pepper.getImage(), pepper.getX(),
          //      pepper.getY(), this);
    }*/
    
    public void loadImage(String path, Image image){
        ImageIcon imageIcon = new ImageIcon(path);
        image = imageIcon.getImage();        
    }
    
    public void drawBook(Graphics g, Book book) {
        
        if(book.isVisible())            
            g.drawImage(book.getImage(), book.getX(), book.getY(), this);        
        else if (book.getY()<=0)
            book.setVisible(true);          
    }

    /*We move the sprite and repaint the part of the board that has changed. 
    We use a small optimisation technique that repaints only the small area 
    of the window that actually changed. */
    /*private void step() {

        pepper.move();
        for (int i = 0; i < imageArray.length; i++) {
            imageArray[i].update();
            checkCollisions();
        }
        ArrayList<Missile> missilesArray = pepper.getMissiles();
        for (int i = 0; i < missilesArray.size(); i++) {
            Missile m = missilesArray.get(i);
            if (m.isVisible() && m.getY() > 0) {
                m.move();
            } else {
                pepper.deleteMissile(m);
            }
        }
        repaint();
    }*/

    /*private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            pepper.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            pepper.keyPressed(e);
        }
    }*/

    /*public void checkCollisions() {

        Rectangle r3 = pepper.getBounds();

        for (int i = 0; i < 7; i++) {
            Rectangle r2 = imageArray[i].getBounds();

            if (r3.intersects(r2) && imageArray[i].isVisibles()) {
                pepper.updateHealth(Obstacle.getDamage());
                punteggio+=MALUS;
                punteggioMalus+=MALUS;
                imageArray[i].setVisibles( false);
                if (!imageArray[i].getCheckCollision()) {
                    pepper.setSt(1);
                    imageArray[i].setCheckCollision(true);                    
                }
                 if(!pepper.isAlive()){
                    ingame=false;
                }
            } else {
                imageArray[i].setCheckCollision(false);
            }

        }

        ArrayList<Missile> missilesArray = pepper.getMissiles();

        for (int j=0; j<missilesArray.size(); j++) {
            Rectangle r33 = missilesArray.get(j).getBounds();

            for (int i = 0; i < 7; i++) {
                Rectangle r2 = imageArray[i].getBounds();

                if (imageArray[i].isVisibles() && r33.intersects(r2)) {                    
                    imageArray[i].setVisibles(false);
                    missilesArray.get(j).setVisible(false);
                    punteggioBonus += BONUS;
                    punteggio += BONUS;
                    
                    //pepper.setSt(4); //DA SISTEMARE
                }
            }
            
        }
    }*/     
    
}
