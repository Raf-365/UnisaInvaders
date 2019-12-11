/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.PepperController;
import Controller.MainController;
import Controller.HudController;
import Controller.BookController;
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

    private Pepper pepper;
    private final int DELAY = 10;
    private Book[] booksArray;
    private ArrayList<Bullet> bulletsArray;
    private static boolean ingame;
    private JProgressBar healthBar;
    private Font font1, font2;
    private JLabel healthLabel, punteggioLabel, bonusLabel, malusLabel;
    private float punteggio;
    private int punteggioBonus, punteggioMalus;
    private MainController mainController;
    private Image imagePepper, imageBook;
    private PepperController pepperController;
    private BookController bookController;
    private HudController hudController;

    public static boolean getIngame() {
        return ingame;
    }

    public MainView() {
        initBoard();
        
    }

    private void initBoard() {
        mainController = MainController.getController(); //SINGLETON
        addKeyListener(new TAdapter());
        setFocusable(true);
        ingame = true;
        //pepper = new Pepper();
        punteggio = 0;
        punteggioBonus = 0;
        punteggioMalus = 0;
        bookController = mainController.getBookController();
        pepperController = mainController.getPepperController();
        pepper = mainController.getPepperController().getPepper();
        hudController = mainController.getHudController();
        graphicsSetup();
        //pepper.addObserver(new SoundPlayerObserver("collide.wav", "check.wav","shot.wav","splat.wav"));
        //pepper.addObserver(new UpdateHealthBarObserver(healthBar));
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(GameFrame.MAX_X, GameFrame.MAX_Y));
        //for (int i = 0; i < imageArray.length; i++) {
        //    imageArray[i] = new Obstacle((i * 70) * -1);
        //}
    }

    private void graphicsSetup() {
        this.setLayout(null);
        font1 = new Font("Helvetica", Font.BOLD, 20);
        font2 = new Font("Helvetica", Font.BOLD, 30);

        punteggioLabel = new JLabel("Punteggio: " + punteggio);
        punteggioLabel.setFont(font1);
        punteggioLabel.setForeground(Color.yellow);
        punteggioLabel.setBounds(5, 90, 200, 30);
        punteggioLabel.setVisible(false);

        bonusLabel = new JLabel("Bonus: " + punteggioBonus);
        bonusLabel.setFont(font1);
        bonusLabel.setForeground(Color.yellow);
        bonusLabel.setBounds(5, 130, 200, 30);
        bonusLabel.setVisible(false);

        malusLabel = new JLabel("Malus: " + punteggioMalus);
        malusLabel.setFont(font1);
        malusLabel.setForeground(Color.yellow);
        malusLabel.setBounds(5, 170, 200, 30);
        malusLabel.setVisible(false);

        healthLabel = new JLabel("HEALTH BAR");
        healthLabel.setFont(font1);
        healthLabel.setForeground(Color.white);
        healthLabel.setBounds(5, 5, 200, 30);

        healthBar = new JProgressBar(0, 5);
        healthBar.setStringPainted(true);
        healthBar.setForeground(Color.red);
        healthBar.setString(Integer.toString(pepper.getHealth()));
        healthBar.setBounds(5, 35, 130, 30);
        healthBar.setValue(pepper.getHealth());
        healthBar.setFont(font2);

        add(healthLabel);
        add(healthBar);
        add(punteggioLabel);
        add(malusLabel);
        add(bonusLabel);
    }

    public void repaintComponents(Book[] booksArray, ArrayList<Bullet> bulletsArray) {
        this.booksArray = booksArray;
        this.bulletsArray = bulletsArray;
        repaint();

    }

    private void drawBullets(Graphics g) {

        for (int i = 0; i < bulletsArray.size(); i++) {
            Bullet b = bulletsArray.get(i);
            if (b.isVisible()) {
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    public void updateLabels() {
        EventQueue.invokeLater(() -> {
            punteggioLabel.setText("Punteggio: " + (int) hudController.getScore());
        });

        EventQueue.invokeLater(() -> {
            malusLabel.setText("Malus: " + hudController.getMalus());
        });

        EventQueue.invokeLater(() -> {
            bonusLabel.setText("Bonus: " + hudController.getBonus());
        });
        
        EventQueue.invokeLater(() -> {
            healthBar.setValue(pepper.getHealth());
            healthBar.setString(Integer.toString(pepper.getHealth()));
        });
        
        
        
        
    }

    @Override
    public void paintComponent(Graphics g) {

        for (int i = 0; i < booksArray.length; i++) {
            // bookController.update();
            checkCollisions();
        }
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

            drawBullets(g);
            updateLabels();
        } else {
            drawGameOver(g);
        }
    }

    private void drawGameOver(Graphics g) {

        String msg = "Game over, you're dead, for this demo is enough, you can stop here!";
        FontMetrics fm = getFontMetrics(font2);

        g.setColor(Color.white);
        g.setFont(font2);
        g.drawString(msg, (GameFrame.MAX_X - fm.stringWidth(msg)) / 2,
                GameFrame.MAX_Y / 2);

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

        Rectangle r3 = pepper.getBounds();

        for (int i = 0; i < 7; i++) {
            Rectangle r2 = booksArray[i].getBounds();

            if (r3.intersects(r2) && booksArray[i].isVisible()) {
                pepper.updateHealth(-1);

                hudController.updateScore(HudController.MALUS);

                hudController.updateMalus(HudController.MALUS);

                booksArray[i].setVisible(false);
                /* if (!booksArray[i].getCheckCollision()) {
                    //pepper.setState(1);
                    booksArray[i].setCheckCollision(true);                    
                }*/
               if(!pepperController.isAlive()){
                    ingame=false;
                }
            } else {
                //imageArray[i].setCheckCollision(false);
            }
           // }

        }

        for (int j = 0; j < bulletsArray.size(); j++) {
            Rectangle r33 = bulletsArray.get(j).getBounds();

            for (int i = 0; i < 7; i++) {
                Rectangle r2 = booksArray[i].getBounds();

                if (booksArray[i].isVisible() && r33.intersects(r2)) {
                    booksArray[i].setVisible(false);
                    bulletsArray.get(j).setVisible(false);
                    hudController.updateScore(HudController.BONUS);

                    hudController.updateBonus(HudController.BONUS);

                    //pepper.setSt(4); //DA SISTEMARE
                }
            }

        }

    }


}
