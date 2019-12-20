/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.BonusController;
import Controller.PepperController;
import Controller.PlayController;
import Controller.HudController;
import Controller.BookController;
import Controller.BossController;
import Controller.HudControllerBoss;
import audio.SoundObservable;
import audio.SoundPlayerCollision;
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
import java.awt.EventQueue;
import java.awt.Image;

/**
 *
 * @author stefa
 */
public class MainView extends JPanel {
    private SoundObservable a;
    private Pepper pepper;
    private Boss boss;
    private final int DELAY = 10;
    private ArrayList<Book> booksArray;
    private ArrayList<Bonus> lifeArray, shieldArray;
    private ArrayList<Bullet> bulletsArray;
    private ArrayList<BulletBoss> bulletsArrayBoss;
    private static boolean ingame;
    private JProgressBar healthBar, healthBarBoss;
    private Font font1, font2;
    private JLabel healthLabel, punteggioLabel, bonusLabel, malusLabel, healthLabelBoss, killedBossLabel;
    private float punteggio;
    private int punteggioBonus, punteggioMalus,numBossKilled;
    private PlayController mainController;
    private Image imagePepper, imageBook;
    private PepperController pepperController;
    private BossController bossController;
    private BookController bookController;
    private BonusController bonusController;
    private HudController hudController;
    private HudControllerBoss hudControllerBoss;
    private GameFrame gameFrame;
    private boolean stopBlinking=false;
    private boolean protection = false;
    
    private Image img = Toolkit.getDefaultToolkit().createImage("src/Resources/unisa_def.jpg");
    
    public static boolean getIngame() {
        return ingame;
    }

    public MainView(GameFrame frame) {
        this.gameFrame=frame;
        this.a=new SoundObservable();
         //a.addListener(new SoundPlayerCollision("splat.wav","splat.wav","splat.wav","splat.wav","splat.wav"));
         a.addListener(new SoundPlayerCollision("collide.wav","splat.wav","check.wav","collide.wav","explo.wav"));
        initBoard();        
    }

    private void initBoard() {
        mainController = PlayController.getPlayController(); //SINGLETON
        addKeyListener(new TAdapter());
        setFocusable(true);
        ingame = true;
        punteggio = 0;
        punteggioBonus = 0;
        punteggioMalus = 0;
        numBossKilled = 0;
        bookController = mainController.getBookController();
        bonusController = mainController.getBonusController();
        pepperController = mainController.getPepperController();
        bossController = mainController.getBossController();
        pepper = mainController.getPepperController().getPepper();
        boss = mainController.getBossController().getBoss();
        hudController = mainController.getHudController();
        hudControllerBoss = mainController.getHudControllerBoss();
        graphicsSetup();
        //pepper.addObserver(new SoundPlayerObserver("collide.wav", "check.wav","shot.wav","splat.wav"));
        //pepper.addObserver(new UpdateHealthBarObserver(healthBar));
        //setBackground(Color.BLACK);
        setPreferredSize(new Dimension(GameFrame.MAX_X, GameFrame.MAX_Y));
        //for (int i = 0; i < imageArray.length; i++) {
        //    imageArray[i] = new Obstacle((i * 70) * -1);
        //}
    }

    private void graphicsSetup() {
        this.setLayout(null);
        font1 = new Font("Helvetica", Font.BOLD, 20);
        font2 = new Font("Helvetica", Font.BOLD, 30);

        punteggioLabel = new JLabel("Score: " + punteggio);
        punteggioLabel.setFont(font1);
        punteggioLabel.setForeground(Color.yellow);
        punteggioLabel.setBounds(5, 90, 200, 30);

        bonusLabel = new JLabel("Bonus: " + punteggioBonus);
        bonusLabel.setFont(font1);
        bonusLabel.setForeground(Color.yellow);
        bonusLabel.setBounds(5, 130, 200, 30);

        malusLabel = new JLabel("Malus: " + punteggioMalus);
        malusLabel.setFont(font1);
        malusLabel.setForeground(Color.yellow);
        malusLabel.setBounds(5, 170, 200, 30);

        killedBossLabel = new JLabel("Killed boss: " + numBossKilled);
        killedBossLabel.setFont(font1);
        killedBossLabel.setForeground(Color.yellow);
        killedBossLabel.setBounds(5, 210, 200, 30);
        
        healthLabel = new JLabel("HEALTH BAR");
        healthLabel.setFont(font1);
        healthLabel.setForeground(Color.white);
        healthLabel.setBounds(5, 5, 200, 30);

        healthBar = new JProgressBar(0, Pepper.HEALTH_MAX);
        healthBar.setStringPainted(true);
        healthBar.setForeground(Color.red);
        healthBar.setString(Integer.toString(pepper.getHealth()));
        healthBar.setBounds(5, 35, 130, 30);
        healthBar.setValue(pepper.getHealth());
        healthBar.setFont(font2);
        
        healthLabelBoss = new JLabel("BOSS HEALTH");
        healthLabelBoss.setFont(font1);
        healthLabelBoss.setForeground(Color.white);
        healthLabelBoss.setBounds(GameFrame.MAX_X-200, 5, 200, 30);

        healthBarBoss = new JProgressBar(0, Boss.HEALTH_MAX);
        healthBarBoss.setStringPainted(true);
        healthBarBoss.setForeground(Color.red);
        healthBarBoss.setString(Integer.toString(boss.getHealth()));
        healthBarBoss.setBounds(GameFrame.MAX_X-200, 35, 130, 30);
        healthBarBoss.setValue(boss.getHealth());
        healthBarBoss.setFont(font2);

        add(healthLabelBoss);
        add(healthLabel);
        add(healthBarBoss);
        add(healthBar);
        add(punteggioLabel);
        add(malusLabel);
        add(bonusLabel);
        add(killedBossLabel);
    }
    
    

    public void repaintComponents(ArrayList<Book> booksArray, ArrayList<Bullet> bulletsArray, 
            ArrayList<BulletBoss> bulletsArrayBoss, ArrayList<Bonus> lifeArray, ArrayList<Bonus> shieldArray) {
        this.booksArray = booksArray;
        this.bulletsArray = bulletsArray;
        this.bulletsArrayBoss=bulletsArrayBoss;
        this.lifeArray = lifeArray;
        this.shieldArray = shieldArray;
        repaint();
    }    

    private void drawBullets(Graphics g) {

        for (int i = 0; i < bulletsArray.size(); i++) {
            Bullet b = bulletsArray.get(i);
            if (b.isVisible()) {
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
        
        
       
              
        for (int i = 0; i < bulletsArrayBoss.size(); i++) {
            BulletBoss b = bulletsArrayBoss.get(i);
            if (b.isVisible()) {
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    public void updateLabels() {
        
        punteggioLabel.setText("Punteggio: " + (int) hudController.getScore());        
        malusLabel.setText("Malus: " + hudController.getMalus());        
        bonusLabel.setText("Bonus: " + hudController.getBonus()); 
        killedBossLabel.setText("Killed boss: "+ bossController.getNumBossKilled());
        healthBar.setValue(pepper.getHealth());
        healthBar.setString(Integer.toString(pepper.getHealth()));
        healthBarBoss.setValue(boss.getHealth());
        healthBarBoss.setString(Integer.toString(boss.getHealth()));
        
        if(!bossController.getBoss().isVisible()){
            healthLabelBoss.setVisible(false);
            healthBarBoss.setValue(boss.getHealth());
            healthBarBoss.setString(Integer.toString(boss.getHealth()));
            healthBarBoss.setVisible(false);
        }
        else{
            healthBarBoss.setVisible(true);
            healthLabelBoss.setVisible(true);
        }            
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        setOpaque(false);
        g.drawImage(img, 0, 0, this);
        //super.paintComponent(g);
        
        if(booksArray != null){ //altrimenti al primo repaint() dopo la removeAll() lancia eccezione
            
            for (int i = 0; i < booksArray.size(); i++) 
                checkCollisions();
        
            if (ingame) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                for (int i = 0; i < booksArray.size(); i++) {
                    Book book = booksArray.get(i);
                    drawBook(g, book);
                    Toolkit.getDefaultToolkit().sync();
                }

                for (int i = 0; i < lifeArray.size(); i++) {
                    Bonus bonus = lifeArray.get(i);
                    drawBonus(g, bonus);
                    Toolkit.getDefaultToolkit().sync();
                }
                
                 for (int i = 0; i < shieldArray.size(); i++) {
                    Bonus bonus = shieldArray.get(i);
                    drawBonus(g, bonus);
                    Toolkit.getDefaultToolkit().sync();
                }
                g2d.drawImage(pepper.getImage(), pepper.getX(), pepper.getY(), this);
                
                if (bossController.isAlive() && boss.isVisible())
                    g2d.drawImage(boss.getImage(), boss.getX(), boss.getY(), this);

                
                drawBullets(g);
                updateLabels();
            } else {
                    drawGameOver(g);
            }
        }
    }

    private void drawGameOver(Graphics g) {
       
        MenuView view = new MenuView(gameFrame);
        view.setMessage("GAME OVER!");
        mainController.setEnabled(false);

        EventQueue.invokeLater(() -> {
            gameFrame.getContentPane().removeAll();
            gameFrame.add(view);
            gameFrame.repaint();
            gameFrame.revalidate();
            //creo una menuview. quando finisce il gioco faccio removeAll, togliendo tutti gli elementi 
            //della grafica, faccio repaint e revalidate per richiamare la view del menu
            view.requestFocus();
        });
    }

    public void loadImage(String path, Image image) {
        ImageIcon imageIcon = new ImageIcon(path);
        image = imageIcon.getImage();
    }

    public void drawBook(Graphics g, Book book) {
        if (book.isVisible()) {
            g.drawImage(book.getImage(), book.getX(), book.getY(), this);
        } else if (book.getY() <= 0) {
            book.setVisible(true);
        }
    }
    
     public void drawBonus(Graphics g, Bonus bonus) {
        if (bonus.isVisible()) {
            g.drawImage(bonus.getImage(), bonus.getX(), bonus.getY(), this);
        } else if (bonus.getY() <= 0) {
            bonus.setVisible(true);
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            pepperController.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            pepperController.keyPressed(e);
        }

    }
    
    
    public void checkCollisions() {

        Rectangle pepperRectangle = pepper.getBounds();
            /*PEPPER CON I LIBRI*/
        for (int i = 0; i < 7; i++) {
            Rectangle r2 = booksArray.get(i).getBounds();

            if (pepperRectangle.intersects(r2) && booksArray.get(i).isVisible()) {
               
                if (bonusController.getProtectionFlag()){//dice se Pepper ha lo scudo o meno
                    
                    hudController.updateScore(HudController.MALUS);
                    //se Pepper viene colpito quando ha uno scudo, gli vengono sottratti punti ma non la vita.
                    booksArray.get(i).setVisible(false);//quando si verifica la collisione, rendo il libro
                    //non visibile.Altrimenti, se pepper non ha lo scudo, aggiorno la vita, lo score e il malus
                }
                else {
                    
                    if(!booksArray.get(i).getCheckCollision()){
                    booksArray.get(i).setCheckCollision(true);
                    a.addStates(2);
                    }
                pepper.updateHealth(-1);

                hudController.updateScore(HudController.MALUS);

                hudController.updateMalus(HudController.MALUS);

                booksArray.get(i).setVisible(false);
                    }
               
               if(!pepperController.isAlive()){
                    ingame=false;
                }
            } else {
                booksArray.get(i).setCheckCollision(false);
            }
          

        }

        /*PEPPER CON I BONUS*/
        
        for (int i = 0; i < 1; i++) {
            Rectangle r2 = bonusController.getLife().get(i).getBounds();

            if (pepperRectangle.intersects(r2) && lifeArray.get(i).isVisible()) {
                a.addStates(3);
                if (pepper.getHealth()<pepper.HEALTH_MAX-1)
                    pepper.updateHealth(2);
                if (pepper.getHealth() == pepper.HEALTH_MAX-1)
                    pepper.updateHealth(1);
                
                

                lifeArray.get(i).setVisible(false);
                if(!lifeArray.get(i).getCheckCollision()){
                    lifeArray.get(i).setCheckCollision(true);
                    a.addStates(3);
                    }
               if(!pepperController.isAlive()){
                    ingame=false;
                }
            } else {
                lifeArray.get(i).setCheckCollision(false);
            }
           

        }  
        /*PEPPER CON GLI SCUDI*/
        for (int i = 0; i < 1; i++) {
            Rectangle r2 = shieldArray.get(i).getBounds();

            if (pepperRectangle.intersects(r2) && shieldArray.get(i).isVisible()) {
              
                //pepper.changeImage(30);
                bonusController.setProtectionFlag(true);
                
                
                

                shieldArray.get(i).setVisible(false);
                 if(!shieldArray.get(i).getCheckCollision()){
                    shieldArray.get(i).setCheckCollision(true);
                    a.addStates(3);
                    }
               if(!pepperController.isAlive()){
                    ingame=false;
                }
            } else {
                shieldArray.get(i).setCheckCollision(false);
            }
           // }

        }  
        
        /*BULLET CON I LIBRI*/
        for (int j = 0; j < bulletsArray.size(); j++) {
            Rectangle r33 = bulletsArray.get(j).getBounds();

            for (int i = 0; i < 7; i++) {
                Rectangle r2 = booksArray.get(i).getBounds();

                if (booksArray.get(i).isVisible() && r33.intersects(r2)) {
                    booksArray.get(i).setVisible(false);
                    bulletsArray.get(j).setVisible(false);
                    hudController.updateScore(HudController.BONUS);
                    a.addStates(1);
                    hudController.updateBonus(HudController.BONUS);
                }
            }

        }
        
        /*BOSS CON I BULLET*/
        Rectangle rBoss = boss.getBounds();
        
            for (int j = 0; j < bulletsArray.size(); j++) {
            Rectangle rBulletsArray = bulletsArray.get(j).getBounds();           

                if (boss.isVisible() && rBulletsArray.intersects(rBoss) && bulletsArray.get(j).isVisible()) {
                    bulletsArray.get(j).setVisible(false);
                    //bulletsArray.get(j).setVisible(false);
                    boss.updateHealth(-1);
                    if(boss.getHealth()==0){
                        a.addStates(5);
                    }
                    if(!bulletsArray.get(j).getCheckCollision()){
                    bulletsArray.get(j).setCheckCollision(true);
                    a.addStates(4);
                    }
                }else{
                     bulletsArray.get(j).setCheckCollision(false);
                }
                
                
            

        }     
            
            
            
        /*PEPPER CN I BULLET DEL BOSS*/
        
        for (int j = 0; j < bulletsArrayBoss.size(); j++) {
            BulletBoss bulletBoss = bulletsArrayBoss.get(j);
            Rectangle rBulletsBoss = bulletBoss.getBounds();
            
            if(bulletBoss.isVisible() && rBulletsBoss.intersects(pepperRectangle)){
                bulletBoss.setVisible(false);
                pepper.updateHealth(-1);
                hudController.updateScore(HudController.MALUS);
                hudController.updateMalus(HudController.MALUS);
                if(!bulletsArrayBoss.get(j).getCheckCollision()){
                    bulletsArrayBoss.get(j).setCheckCollision(true);
                    a.addStates(4);
                    }
                if(!pepperController.isAlive()){
                    ingame=false;
                }
            }else{
                 bulletsArrayBoss.get(j).setCheckCollision(false);
            }
                
        }
            
    }

    
    
    }
