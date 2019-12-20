/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author stefa
 */
public class Game implements Runnable {

    private PlayController playController;
    private static final int DELAY = 10, SECONDS_BOOK_DISAPPEAR = 10, SECONDS_BONUS_DISAPPEAR = 3, SECONDS_SPEED_BOOKS_UPDATE = 5;
    private boolean disableSpeedUpdateFlag = false, disappearBookFlag = false, flag = false;

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

        long bookSpeedUpdateTime, bookDisappearTime, beforeTime, timeDiff, sleep, bonusSpeedUpdateTime, bonusSpeedTimeBefore = 0, bonusSpeedTimeAfter = 0;

        bookSpeedUpdateTime = System.currentTimeMillis();
        bookDisappearTime = bookSpeedUpdateTime;
        beforeTime = System.currentTimeMillis();

        while (true) {//playController.getIngame()

            /*AUMENTO DELLA VELOCITA' DEI LIBRI DOPO TOT SECONDI*/
            if (playController.isEnabled() && !disableSpeedUpdateFlag
                    && (beforeTime - bookSpeedUpdateTime) / 1000 > SECONDS_SPEED_BOOKS_UPDATE) {
                playController.updateFallingObjectSpeed();

                bookSpeedUpdateTime = System.currentTimeMillis();
            }

             /*Scomparsa dei bonus dopo una sola volta  */
            if (playController.isEnabled() && !disappearBookFlag
                    && (beforeTime - bookDisappearTime) / 1000 > SECONDS_BONUS_DISAPPEAR) {
                disappearBookFlag = true;
               
                playController.getBonusController().setDisappearBonusFlag(disappearBookFlag);
                 disappearBookFlag = false;
                
            }
            
            
            /*SCOMPARSA DEI LIBRI DOPO TOT SECONDI E COMPARSA DEL BOSS*/
            if (playController.isEnabled() && !disappearBookFlag
                    && (beforeTime - bookDisappearTime) / 1000 > SECONDS_BOOK_DISAPPEAR) {
                disappearBookFlag = true;
                playController.getBookController().setDisappearBookFlag(disappearBookFlag);
                playController.getBonusController().setDisappearBonusFlag(disappearBookFlag);
                disableSpeedUpdateFlag = true;
                
            }

            //SCUDO DI PEPPER 
            if (playController.isEnabled() && playController.getBonusController().getProtectionFlag()) {

                if (!flag) {
                    bonusSpeedTimeBefore = System.currentTimeMillis();
                    flag = !flag;
                }

                if ((System.currentTimeMillis() - bonusSpeedTimeBefore) / 1000 < 5) {

                    playController.getPepperController().getPepper().changeImage(30);

                    if (playController.getPepperController().getFireFlag() == true) {
                        playController.getPepperController().getPepper().changeImage(40);
                    } else {
                        playController.getPepperController().getPepper().changeImage(30);
                    }

                } else {

                    playController.getBonusController().setProtectionFlag(false);
                    playController.getPepperController().getPepper().changeImage(20);
                    flag = false;
                }
            }

            /*AGGIORNAMENTO DELLE VARIABILI DOPO LA MORTE DEL BOSS*/
            if (playController.isEnabled() && !playController.getBossController().isAlive()) {
                playController.getBossController().getBoss().setVisible(false);
                playController.resetBossController();
                playController.getBossController().updateKilledBoss();
                playController.getHudController().updateScore(HudController.KILLED_BOSS);
                playController.getHudController().updateBonus(HudController.KILLED_BOSS);
                disappearBookFlag = false;
                playController.getBookController().setDisappearBookFlag(disappearBookFlag);
                playController.getBonusController().setDisappearBonusFlag(disappearBookFlag);
                disableSpeedUpdateFlag = false;
                bookSpeedUpdateTime = System.currentTimeMillis();
                bookDisappearTime = bookSpeedUpdateTime;
            }
        /*AGGIORNAMENTO DEL CONTROLLER*/
            if (playController.isEnabled()) {
                playController.update();
            }  
            
       
            /*RESETTO LE VARIABILI E L'ATTESA DEI SECONDI*/
            else{
                disableSpeedUpdateFlag = false; 
                disappearBookFlag = false;
                bookSpeedUpdateTime = System.currentTimeMillis(); 
                bookDisappearTime = System.currentTimeMillis();
            }               
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 1;
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
