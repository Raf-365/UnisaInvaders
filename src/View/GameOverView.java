/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.PlayController;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author antno
 */
public class GameOverView extends JPanel implements ActionListener{
     private GameFrame gameFrame;
     private JButton  startButton, exitButton;
     Image im;
     private PlayController p;
    public GameOverView(GameFrame gameFrame,PlayController p) {
        this.gameFrame = gameFrame;
        this.p=p;
        this.im=Toolkit.getDefaultToolkit().createImage("src/Resources/go.jpg");
        p.setEnabled(false);
        graphicSetup();
    }
    public void initUi() {
        MenuView view = new MenuView(gameFrame);
        view.setMessage("GAME OVER!");
            EventQueue.invokeLater(() -> {
                gameFrame.getContentPane().removeAll();
                gameFrame.add(view);
                gameFrame.repaint();
                gameFrame.revalidate();
                view.requestFocus();
            
            });
       
    }
    private void graphicSetup(){
        this.setLayout(null);   
        Font font, font2;
        font = new Font("Helvetica", Font.BOLD, 21);
        font2 = new Font("Helvetica", Font.BOLD, 21);
   
        startButton = new JButton("MAIN MENU");
        startButton.setBackground(Color.white);
        startButton.setFocusPainted(false);
        startButton.setEnabled(true);
        exitButton = new JButton("EXIT");  
        exitButton.setFocusPainted(false);
        exitButton.setBackground(Color.white);
        startButton.setFont(font);        
        exitButton.setFont(font); 
        startButton.setBounds((GameFrame.MAX_X-1100)/2, GameFrame.MAX_Y/2-85, 250, 50); 
        exitButton.setBounds((GameFrame.MAX_X-1100)/2, GameFrame.MAX_Y/2-20, 250, 50);
        startButton.addActionListener(this);
        exitButton.addActionListener(this);        
        add(startButton);
        add(exitButton);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton){
          initUi();
        }        
        
        if(e.getSource() == exitButton)
            System.exit(0);
    }
     
       @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        setOpaque(false);
        g.drawImage(im, 0, 0,GameFrame.MAX_X, GameFrame.MAX_Y, this);
        setOpaque(false);
        }
    }

