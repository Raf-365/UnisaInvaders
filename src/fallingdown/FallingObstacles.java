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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author antno
 */
public class FallingObstacles extends JPanel implements Runnable{
        private final int B_WIDTH = 350;
    private final int B_HEIGHT = 350;
    private final int INIT_X = -40;
    private final int INIT_Y = -40;
    private final int DEL = 25;

    private Image elem;
    private Thread anim;
    private int x, y;
    
    public FallingObstacles(){
     initBoard();
    }
    
    private void initBoard() {

        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        loadImage();

        x = INIT_X;
        y = INIT_Y;
    }
    private void loadImage() {

        ImageIcon im = new ImageIcon("src/sokoban/wall.png");
        elem = im.getImage();
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

           // cycle();
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
                System.out.println("error, mast");
            }
        }
    }
    
}

