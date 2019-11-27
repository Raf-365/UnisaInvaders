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
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author antno
 */
public class Obstacle extends JPanel/* implements Runnable*/{
   /* private final int B_WIDTH = 350;
    private final int B_HEIGHT = 350;
    private final int INIT_X = 0;
    private final int INIT_Y = -10;*/
    
    private ImageIcon image;
    private Image elem;
    private int x, y;
    /*private final int DEL = 25;*/

   /* private Image[] imageArray = new Obstacle[3];*/

   
    /*private Thread anim;*/
    
    
    public Obstacle(int y){
    image=new ImageIcon("src/sokoban/wall.png");
    elem= image.getImage();
    x=generateRandom();
    this.y=y;
    }
    
    private int generateRandom(){
    Random random = new Random();
    return random.nextInt(Obstaclessss.B_WIDTH-20)+10;
    }
    
   /* private void initBoard() {

        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        loadImage();

        x = INIT_X;
        y = INIT_Y;
    }*/
    
    
   /* private void loadImage() {

        /*ImageIcon im = new ImageIcon("src/sokoban/wall.png");
        elem = im.getImage();
    } */
    
    
    
    /*@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawObstacle(g);
    }
    */
    /*private*/ public void drawObstacle(Graphics g) {

        g.drawImage(elem, x, y, this);
        /*Toolkit.getDefaultToolkit().sync();*/
    }
     
    
   /* @Override
    public void addNotify() {
        super.addNotify();

        anim = new Thread(this);
        anim.start();
    }*/
    
        private void cycle() {

        //x += 1;
        y += 2;

        if (y > Obstaclessss.B_HEIGHT) {

            y = -10;
            x = generateRandom();
        }
    }
    
        public void update(){
            cycle();
    }
        
        }
    
   /*  @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        while (true) {

            cycle();
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
    }*/
    


