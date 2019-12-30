package View;

import Controller.BonusController;
import Controller.PepperController;
import Controller.PlayController;
import Controller.HudController;
import Controller.BookController;
import Controller.BossController;
import ObserverPackage.CollisionEvent;
import ObserverPackage.Observer;
import TemplatePackage.BulletPepperCollideBook;
import TemplatePackage.BulletPepperCollideBoss;
import TemplatePackage.PepperCollideLife;
import TemplatePackage.PepperCollideBook;
import TemplatePackage.PepperCollideBulletBoss;
import TemplatePackage.PepperCollideShield;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;
import entities.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.EventQueue;
import java.awt.Image;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import main.Game;

public class MainView extends JPanel implements Observer  {

    private Pepper pepper;
    private Boss boss;
    private final int DELAY = 10;
    public static final int PEPPER_COLLIDE_BOOK = 1, PEPPER_COLLIDE_LIFE = 2, PEPPER_COLLIDE_SHIELD = 3,
                            BULLET_PEPPER_COLLIDE_BOOK = 4, BULLET_PEPPER_COLLIDE_BOSS = 5, PEPPER_COLLIDE_BULLET_BOSS = 6;
    
    private ArrayList<Book> booksArray;
    private Bonus life, shield;
    private ArrayList<Bullet> bulletsArray;
    private ArrayList<BulletBoss> bulletsArrayBoss;
    private boolean ingame;

    public void setIngame(boolean ingame) {
        this.ingame = ingame;
    }
    private JProgressBar healthBar, healthBarBoss, remainingShieldBar;
    private Font font1, font2;
    private JLabel healthLabel, punteggioLabel, bonusLabel, malusLabel, healthLabelBoss, killedBossLabel, remainingShieldLabel;
    private float punteggio;
    private int punteggioBonus, punteggioMalus,numBossKilled;
    private PlayController playController;
    private Image imagePepper, imageBook;
    private PepperController pepperController;
    private BossController bossController;
    private BookController bookController;
    private BonusController bonusController;
    private HudController hudController;
    //private HudControllerBoss hudControllerBoss;
    private GameFrame gameFrame;
    private boolean stopBlinking=false;
    private boolean protection = false;
    private boolean drawLifeFlag = false;
    private boolean drawShieldFlag = false;
    private Image img = Toolkit.getDefaultToolkit().createImage("src/Resources/sfondo.jpg");
    private ArrayList<Observer> observers;
    private ArrayList<Integer> states;
    private AudioClip clip;
    private AudioClip clip2;
    private AudioClip clip3;
    private AudioClip clip4;
    private AudioClip clip5;
    private AudioClip clip6;
    private AudioClip clip7;
    PepperCollideBook pepperCollideBook = new PepperCollideBook();
    PepperCollideLife pepperCollideLife = new PepperCollideLife();
    PepperCollideShield pepperCollideShield = new PepperCollideShield();
    BulletPepperCollideBoss bulletPepperCollideBoss = new BulletPepperCollideBoss();
    PepperCollideBulletBoss pepperCollideBulletBoss = new PepperCollideBulletBoss();
    BulletPepperCollideBook bulletPepperCollideBook = new BulletPepperCollideBook();
    
    public boolean getIngame() {
        return ingame;
    }

    public MainView(GameFrame frame) {
        this.gameFrame=frame;
        
        initBoard(); 
        
        
        
    }

    private void initBoard() {
        playController = PlayController.getPlayController(); //SINGLETON
        addKeyListener(new TAdapter());
        setFocusable(true);
        ingame = true;
        punteggio = 0;
        punteggioBonus = 0;
        punteggioMalus = 0;
        numBossKilled = 0;
        
        //CHIEDERE A FOGGIA SE DOBBIAMO CREARE LE NOSTRE ENTITA' IN QUESTA CLASSE O NEI CONTROLLER E LASCIARE COSI'
        
        bookController = playController.getBookController();
        bonusController = playController.getBonusController();
        pepperController = playController.getPepperController();
        bossController = playController.getBossController();
        pepper = playController.getPepperController().getPepper();
        boss = playController.getBossController().getBoss();
        hudController = playController.getHudController();
        //hudControllerBoss = playController.getHudControllerBoss();
        life = bonusController.getLife();
        shield = bonusController.getShield();
        
        booksArray = bookController.getBooks();
        bulletsArray = pepperController.getBulletsArray();
        bulletsArrayBoss = bossController.getBulletsArrayBoss();
                
        
        
        /*Serve per rendere la mainView osservata dal playController*/
        this.states = new ArrayList<Integer>();
        this.observers = new ArrayList<Observer>();
        this.addObserver((Observer) playController);  
        bossController.getBoss().addObserver(this);
        pepperController.getPepper().addObserver(this);
        URL url = getClass().getResource("shot.wav");
        URL url2 = getClass().getResource("collide.wav");        
        URL url3 = getClass().getResource("hitHeadSound.wav");
        URL url4 = getClass().getResource("hitHeadSound.wav");
        URL url5 = getClass().getResource("collide.wav");
        URL url6 = getClass().getResource("bonusSound.wav");
        URL url7 = getClass().getResource("gameOver.wav");          
        clip =  Applet.newAudioClip(url);
        clip2 = Applet.newAudioClip(url2);
        clip3 = Applet.newAudioClip(url3);
        clip4 = Applet.newAudioClip(url4);
        clip5 = Applet.newAudioClip(url5);
        clip6 = Applet.newAudioClip(url6);
        clip7= Applet.newAudioClip(url7);
        
        
        graphicsSetup();
        setPreferredSize(new Dimension(GameFrame.MAX_X, GameFrame.MAX_Y));
        
    }

    public AudioClip getClip() {
        return clip;
    }

    public AudioClip getClip2() {
        return clip2;
    }

    public AudioClip getClip3() {
        return clip3;
    }

    public AudioClip getClip4() {
        return clip4;
    }

    public AudioClip getClip5() {
        return clip5;
    }

    public AudioClip getClip6() {
        return clip6;
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
        bonusLabel.setBounds(5, 120, 200, 30);

        malusLabel = new JLabel("Malus: " + punteggioMalus);
        malusLabel.setFont(font1);
        malusLabel.setForeground(Color.yellow);
        malusLabel.setBounds(5, 150, 200, 30);

        killedBossLabel = new JLabel("Killed boss: " + numBossKilled);
        killedBossLabel.setFont(font1);
        killedBossLabel.setForeground(Color.yellow);
        killedBossLabel.setBounds(5, 180, 200, 30);
        
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
        
        remainingShieldLabel = new JLabel("REMAINING SHIELD");
        remainingShieldLabel.setFont(font1);
        remainingShieldLabel.setForeground(Color.white);
        remainingShieldLabel.setBounds(5, 250, 200, 30);
        
        remainingShieldBar = new JProgressBar(0, Game.SECONDS_SHIELD_DISAPPEAR); //5 secondi
        remainingShieldBar.setStringPainted(true);
        remainingShieldBar.setForeground(Color.red);
        remainingShieldBar.setBounds(5, 290, 130, 30);
        remainingShieldBar.setValue(pepper.getHealth());
        remainingShieldBar.setFont(font2);
        
        remainingShieldLabel.setVisible(false);
        remainingShieldBar.setVisible(false);

        add(remainingShieldBar);
        add(remainingShieldLabel);
        add(healthLabelBoss);
        add(healthLabel);
        add(healthBarBoss);
        add(healthBar);
        add(punteggioLabel);
        add(malusLabel);
        add(bonusLabel);
        add(killedBossLabel);
        
        
    }
    
    
    
    
    public void repaintComponents() {
        
        repaint();
    }    

    private void drawBullets(Graphics g) {

        for (int i = 0; i < bulletsArray.size(); i++) {
            Bullet b = bulletsArray.get(i);
            if (b.isVisible()) 
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
        }
              
        for (int i = 0; i < bulletsArrayBoss.size(); i++) {
            BulletBoss b = bulletsArrayBoss.get(i);
            if (b.isVisible()) 
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
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
        
        if(bonusController.getProtectionFlag()){
            remainingShieldLabel.setVisible(true);
            remainingShieldBar.setVisible(true);
            remainingShieldBar.setValue((int)bonusController.getShieldDuration());
        }
        
        else{
            remainingShieldLabel.setVisible(false);
            remainingShieldBar.setVisible(false);
        }
        
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
               
                if (drawLifeFlag == true){
                     drawBonus(g, life);
                    }
                
                if (drawShieldFlag == true){
                     drawBonus(g, shield);
                }
                
                 g2d.drawImage(pepper.getImage(), pepper.getX(), pepper.getY(), this);
                
                if (bossController.isAlive() && boss.isVisible())
                    g2d.drawImage(boss.getImage(), boss.getX(), boss.getY(), this);

                drawBullets(g);
                updateLabels();
                
            } else 
                drawGameOver(g);            
        }
    }

    private void drawGameOver(Graphics g) {
        clip7.play();
        playController.setEnabled(false);
        try{
        Thread.sleep(2500);
        MenuView view = new MenuView(gameFrame);
        view.setMessage("GAME OVER!");
        
        
        
        EventQueue.invokeLater(() -> {
            gameFrame.getContentPane().removeAll();
            gameFrame.add(view);
            gameFrame.repaint();
            gameFrame.revalidate();
            view.requestFocus();
        });}
        catch(InterruptedException e ){
            
        }
    }

    public void loadImage(String path, Image image) {
        ImageIcon imageIcon = new ImageIcon(path);
        image = imageIcon.getImage();
    }

    public void drawBook(Graphics g, Book book) {
        if (book.isVisible()) 
            g.drawImage(book.getImage(), book.getX(), book.getY(), this);
        else if (book.getY() <= 0)
            book.setVisible(true);
    }
    
    public void drawBonus(Graphics g, Bonus bonus) {
        if (bonus.isVisible())
            g.drawImage(bonus.getImage(), bonus.getX(), bonus.getY(), this);
        else if (bonus.getY() <= 0)
            bonus.setVisible(true);
    }

    @Override
    public void eventCollisionChanged(CollisionEvent event) {
        if (event.getState().contains(PlayController.BOSS_NOT_VISIBLE)){
            bossController.getBoss().setVisible(false);
            Boss bo =(Boss)event.getSource();
            bo.removeState(PlayController.BOSS_NOT_VISIBLE);
        }if (event.getState().contains(PlayController.BOSS_IS_VISIBLE)){
            bossController.getBoss().setVisible(true);
            Boss bo =(Boss)event.getSource();
            bo.removeState(PlayController.BOSS_IS_VISIBLE); 
        }if (event.getState().contains(PlayController.LIFE_UPDATE)){
            drawLifeFlag = true;
            Bonus pep =(Bonus)event.getSource();
            pep.removeState(PlayController.LIFE_UPDATE);
        }if (event.getState().contains(PlayController.SHIELD_UPDATE)){
            drawShieldFlag = true;
            Bonus pep1 =(Bonus)event.getSource();
            pep1.removeState(PlayController.SHIELD_UPDATE);
        }if (event.getState().contains(PlayController.SOUND_BULLET)){
            clip.play();
            Pepper pep2 =(Pepper)event.getSource();
            pep2.removeState(PlayController.SOUND_BULLET);
        }if (event.getState().contains(PlayController.PEPPER_DEATH)){
            
            ingame = false;
            
        Pepper pep11 =(Pepper)event.getSource();
        pep11.removeState(PlayController.PEPPER_DEATH);
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
       
        pepperCollideBook.collision(this, (ArrayList)booksArray, pepper);
        pepperCollideLife.collision(this, life, pepper);
        pepperCollideShield.collision(this, shield, pepper);
        bulletPepperCollideBoss.collision(this,(ArrayList)bulletsArray, boss);
        pepperCollideBulletBoss.collision(this,(ArrayList)bulletsArrayBoss, pepper);
        
         for (int j = 0; j < bulletsArray.size(); j++) {
            Bullet bullet = bulletsArray.get(j);
            bulletPepperCollideBook.collision(this, (ArrayList)booksArray, bullet);
         }

    }
    
    
     public synchronized void addStates(int state) {
        states.add(state);
        stateChanged();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public synchronized void removeState(int state) {
        int ind = states.indexOf(state);
        states.remove(ind);

    }

    public ArrayList<Integer> getList() {
        return states;
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    protected void stateChanged() {
        CollisionEvent collisionEvent = new CollisionEvent(this, getList());
       
        for (Observer eachListener : observers) 
            eachListener.eventCollisionChanged(collisionEvent);
        
    }

    
}
