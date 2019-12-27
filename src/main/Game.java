package main;
import View.GameFrame;
import View.MainView;
import Controller.*;
import View.MenuView;
import entities.Book;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Game implements Runnable {

    private PlayController playController;
    public static final int DELAY = 10, SECONDS_BOOK_DISAPPEAR = 10, SECONDS_BONUS_DISAPPEAR = 3, 
            SECONDS_SPEED_BOOKS_UPDATE = 5, SECONDS_SHIELD_DISAPPEAR = 5;
    

    public Game() {
        playController = PlayController.getPlayController();
    }

    public static void main(String[] args) {
        
        Game game = new Game();
        GameFrame frame = new GameFrame();
        MenuView menuView = new MenuView(frame);
        
        
        EventQueue.invokeLater(() -> {
            frame.add(menuView);
            frame.setVisible(true);
        });

        Thread animator = new Thread(game);
        animator.start();
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        while (true) {//playController.getIngame()

            /*AGGIORNAMENTO DEL CONTROLLER*/
            if(playController.isEnabled())
                playController.update();            
       
            /*RESETTO LE VARIABILI E L'ATTESA DEI SECONDI*/ 
           else {
               playController.setDisableSpeedUpdateFlag(false);
               playController.setDisappearBookFlag(false);
               playController.setBookSpeedUpdateTime(System.currentTimeMillis());
               playController.setBookDisappearTime(System.currentTimeMillis());
           }
                           
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) 
                sleep = 1;
            

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
