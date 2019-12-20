/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.*;
import entities.Book;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author stefa
 */
public class MenuView extends JPanel implements ActionListener{
    
    private JButton resetDifficulty, startButton, exitButton, easyDifficulty, mediumDifficulty, hardDifficulty;
    private PlayController playController;
    private GameFrame gameFrame;
    private String message=null;
    private Font font, font2;
    private JLabel difficulty;
    
    Image img = Toolkit.getDefaultToolkit().createImage("src/Resources/peppermenu.png");
    
    public MenuView(GameFrame frame){
        
        playController = PlayController.getPlayController();
        this.gameFrame=frame;
        graphicSetup();
    }

    public void setMessage(String message) {this.message = message;}   
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //setOpaque(false);
        g.drawImage(img, 0, 0, this);
        if(message != null){
            
            FontMetrics fm = getFontMetrics(font);        
            g.setColor(Color.red);
            g.setFont(font);
            g.drawString(message, (GameFrame.MAX_X-1080)/2, GameFrame.MAX_Y/2-205);
        }
    }
    
    private void graphicSetup(){
        this.setLayout(null);   
       
        
        //this.setBackground(Color.black);
        
        
        font = new Font("Helvetica", Font.BOLD, 30);
        font2 = new Font("Helvetica", Font.BOLD, 25);
        
        difficulty = new JLabel("DIFFICULTY");
        difficulty.setForeground(Color.red);
        
        startButton = new JButton("START");
        startButton.setBackground(Color.white);
        startButton.setFocusPainted(false);
        startButton.setEnabled(false);
        
        easyDifficulty = new JButton("1");  
        easyDifficulty.setFocusPainted(false);
        easyDifficulty.setBackground(Color.white);
        
        mediumDifficulty = new JButton("2");  
        mediumDifficulty.setFocusPainted(false);
        mediumDifficulty.setBackground(Color.white);
        
        hardDifficulty = new JButton("3");  
        hardDifficulty.setFocusPainted(false);
        hardDifficulty.setBackground(Color.white);
        
        exitButton = new JButton("EXIT");  
        exitButton.setFocusPainted(false);
        exitButton.setBackground(Color.white);
        
        resetDifficulty = new JButton("RESET");  
        resetDifficulty.setFocusPainted(false);
        resetDifficulty.setBackground(Color.white);
        
        
        difficulty.setFont(font);
        
        startButton.setFont(font);        
        hardDifficulty.setFont(font2);
        mediumDifficulty.setFont(font2);
        easyDifficulty.setFont(font2);
        
        exitButton.setFont(font); 
        
        resetDifficulty.setFont(font2);
        //nel setbounds i primi due parametri indicano la posizione del bottone e gli ultimi 2 le dimensioni,larghezza, altezza
        difficulty.setBounds((GameFrame.MAX_X-1065)/2, GameFrame.MAX_Y/2-200, 200, 50);
        startButton.setBounds((GameFrame.MAX_X-1100)/2, GameFrame.MAX_Y/2-85, 200, 50);
        hardDifficulty.setBounds((GameFrame.MAX_X-800)/2, GameFrame.MAX_Y/2-150, 50, 50);
        mediumDifficulty.setBounds((GameFrame.MAX_X-950)/2, GameFrame.MAX_Y/2-150, 50, 50);
        easyDifficulty.setBounds((GameFrame.MAX_X-1100)/2, GameFrame.MAX_Y/2-150, 50, 50);
        exitButton.setBounds((GameFrame.MAX_X-1100)/2, GameFrame.MAX_Y/2-20, 200, 50);
        resetDifficulty.setBounds((GameFrame.MAX_X-1100)/2, GameFrame.MAX_Y/2+45, 200, 50);
        
        startButton.addActionListener(this);
        easyDifficulty.addActionListener(this);
        mediumDifficulty.addActionListener(this);
        hardDifficulty.addActionListener(this);
        exitButton.addActionListener(this);
        resetDifficulty.addActionListener(this);
        
        add(difficulty);        
        add(startButton);
        add(easyDifficulty);
        add(mediumDifficulty);
        add(hardDifficulty);
        add(exitButton);
        add(resetDifficulty);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton){
            
            
            playController.initController();
            MainView view = new MainView(gameFrame);
            playController.setView(view);
            playController.setEnabled(true);            
            
            EventQueue.invokeLater(() -> {            
                gameFrame.getContentPane().removeAll();
                gameFrame.add(view);
                gameFrame.repaint();
                gameFrame.revalidate();
                view.requestFocus();
            });
        }
        
        
        if(e.getSource() == exitButton){
            System.exit(0);
        }
        
        if(e.getSource() == hardDifficulty){
            startButton.setEnabled(true);
            mediumDifficulty.setEnabled(false);
            easyDifficulty.setEnabled(false);
            Book.setSpeed(BookController.HARD_BOOK);
            
        }
        
        if(e.getSource() == mediumDifficulty){
            startButton.setEnabled(true);
            easyDifficulty.setEnabled(false);
            hardDifficulty.setEnabled(false);
            Book.setSpeed(BookController.MEDIUM_BOOK);
            
        }
        if(e.getSource() == easyDifficulty){
            startButton.setEnabled(true);
            mediumDifficulty.setEnabled(false);  
            hardDifficulty.setEnabled(false);
            Book.setSpeed(BookController.EASY_BOOK);    
        }
        
        if(e.getSource() == resetDifficulty){
            startButton.setEnabled(false);
            mediumDifficulty.setEnabled(true);  
            hardDifficulty.setEnabled(true);  
            easyDifficulty.setEnabled(true);   
        }
        
    }   
}
