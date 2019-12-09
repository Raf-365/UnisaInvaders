/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author stefa
 */
public class Game implements Runnable{
    
    private static MainController mainController;
    private static final int DELAY=10;
    
    public Game(){
        mainController = MainController.getController();
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        MainView mainView=new MainView();
        mainController.setView(mainView);
        //int w = GameFrame.MAX_X-mainView.getPepperImage().getWidth(null);
        //int h = GameFrame.MAX_X-mainView.getPepperImage().getHeight(null);
        
        //mainController.getPepperController().getPepper().setX((GameFrame.MAX_X-200)/2);          
        //mainController.getPepperController().getPepper().setY((GameFrame.MAX_Y-200-GameFrame.INF_BORDER));
        
        EventQueue.invokeLater(() -> {
            GameFrame frame = new GameFrame();
            frame.add(mainView);
            frame.setVisible(true);
        });        
                
        Thread animator = new Thread(game);
        animator.start();
    }
    
    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (mainController.getIngame()) {
            
            mainController.update();
            /*
            punteggio += 0.01;
            EventQueue.invokeLater(() -> {
                punteggioLabel.setText("Punteggio: "+(int)punteggio);
            });
            
            EventQueue.invokeLater(() -> {
                malusLabel.setText("Malus: "+ punteggioMalus);
            });
            
            EventQueue.invokeLater(() -> {
                bonusLabel.setText("Bonus: "+punteggioBonus);
            });
            */
            
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                
                String msg = String.format("Thread interrupted: %s", e.getMessage());                
                JOptionPane.showMessageDialog(null, msg, "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
        }
        
    }
    
}
